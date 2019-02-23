package com.xinyuan.data.service;

/**
 * @author liang
 */

import com.xinyuan.base.common.service.Order;
import com.xinyuan.base.common.web.Conditions;
import com.xinyuan.data.model.TableQuery;
import lombok.Data;

import java.util.List;

/**
 * sql拼接对象
 */
@Data
public class SqlStitching {


    /**
     * 查询对象
     */
    private TableQuery tableQuery;


    public SqlStitching() {
    }

    /**
     * @param tableQuery 查询请求
     */
    public SqlStitching(TableQuery tableQuery) {
        this.tableQuery = tableQuery;
    }

    /**
     * 追加查询条件
     *
     * @param querySql
     * @param conditions
     * @return
     */
    public static String appendCondition(String querySql, List<Conditions> conditions) {
        StringBuffer sql = new StringBuffer(querySql);
        //条件拼接
        sql.append(getConditionSql(conditions));
        return sql.toString();
    }

    /**
     * 追加排序条件
     *
     * @param querySql
     * @param orders
     * @return
     */
    public static String appendOrders(String querySql, List<Order> orders) {
        StringBuffer sql = new StringBuffer(querySql);
        sql.append(getOrderSql(orders));
        return sql.toString();
    }


    /**
     * 获得最终sql 不带条件和排序
     *
     * @return 获得sql
     */
    public static String getSql(String dataQuerySql, List<String> columns, boolean isMultiple) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        for (String column : columns) {
            sql.append(column).append(",");
        }
        if (isMultiple) {
            sql.append(" pk_id, ");
            sql.append(" rw , ");
        }
        sql.append("serial_id");
        sql.append(" FROM ");
        //单记录直接转换
        sql.append(dataQuerySql);

        return sql.toString();
    }


    /**
     * 获得最终sql 不带条件和排序
     *
     * @return 获得sql
     */
    public static String getCountSql(String dataQuerySql) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" count(*) ");
        sql.append(" FROM ");
        //单记录直接转换
        sql.append(dataQuerySql);

        return sql.toString();
    }

    /**
     * 只有多记录会进来
     * 多记录会通过一个字段进行排序
     *
     * @return
     */
    public static String multipleOrder(String querySql, String orderColumn, List<String> columns) {
        StringBuffer sql = new StringBuffer();
        sql.append(" (SELECT ");
        for (String column : columns) {
            sql.append(column).append(",");
        }
        sql.append("pk_id,");
        sql.append("serial_id,");
        sql.append("(ROW_NUMBER() OVER(partition by  serial_id  order by " + orderColumn + " ,pk_id )) rw");

        sql.append(" FROM ");
        sql.append(querySql);
        sql.append(") dd ");
        return sql.toString();
    }


    /**
     * 竖表转横表的sql拼接
     * 单 多 记录 都一样
     *
     * @return
     */
    public static String trans(String filterSql, List<String> columns, boolean isMultiple) {
        StringBuffer sql = new StringBuffer();
        sql.append(" ( SELECT ");
        sql.append(tranSql(columns));
        if (isMultiple) {
            sql.append(" pk_id, ");
        }
        sql.append(" serial_id ");
        sql.append(" FROM ");
        sql.append(" ( ");
        sql.append(filterSql);
        sql.append(" ) bb ");
        sql.append(" GROUP BY ");
        sql.append(" serial_id ");
        if (isMultiple) {
            sql.append(" , pk_id ");
        }
        sql.append(" ) cc  ");
        return sql.toString();
    }

    /**
     * 过滤数据 只查询最新的 倒数第二层
     * 单 多 记录 都一样
     */
    public static String filter(String dataQuerySql, boolean isMultiple) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" serial_id, ");
        sql.append(" column_name, ");
        sql.append(" start_time, ");
        sql.append(" data_value, ");
        sql.append(" c_id ");
        if (isMultiple) {
            sql.append(" , pk_id ");
        }
        sql.append(" FROM ");
        sql.append(" (");
        //
        sql.append(dataQuerySql);
        sql.append(" ) aa ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND aa.rw = 1 ");
        return sql.toString();
    }


    /**
     * 拼接最里层的查询sql语句
     * 单 多 记录 都一样
     *
     * @return
     */
    public static String dataQuerySql(String tableName, boolean isMultiple) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" serial_id,");
        sql.append(" column_name,");
        sql.append(" start_time,");
        sql.append(" data_value,");
        sql.append(" c_id,");
        if (isMultiple) {
            sql.append(" pk_id, ");
        }
        if (isMultiple) {
            sql.append(" ( ROW_NUMBER () OVER ( PARTITION BY serial_id , pk_id, column_name ORDER BY start_time DESC, show_flag DESC, operate_time DESC )) rw ");
        } else {
            sql.append(" ( ROW_NUMBER () OVER ( PARTITION BY serial_id, column_name ORDER BY start_time DESC, show_flag DESC, operate_time DESC )) rw ");
        }
        sql.append(" FROM ");
        sql.append(" \"").append(tableName).append("\"");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND status in (:status) ");
        sql.append(" AND column_name in(:" + tableName + "_columns) ");
        sql.append(" AND to_date('yyyy-MM-dd' ,start_time) < :startTime ");
        return sql.toString();
    }


    /**
     * 通过column字段拼接竖表转横标的函数
     *
     * @param columns 列
     * @return 动态转换部分的sql
     */
    private static String tranSql(List<String> columns) {
        StringBuffer sql = new StringBuffer();
        for (String column : columns) {
            sql.append(" MAX ");
            sql.append(" ( ");
            sql.append(" CASE column_name WHEN ");
            sql.append("'").append(column).append("'");
            sql.append(" THEN data_value ELSE '' END ");
            sql.append(" ) ");
            sql.append(column).append(",");
        }
        return sql.toString();
    }


    /**
     * 拼接排序语句
     *
     * @param orders 排序集合
     * @return Order B有后面的所有语句
     */
    public static String getOrderSql(List<Order> orders) {
        StringBuffer sql = new StringBuffer();

        if (orders == null || orders.isEmpty() || orders.size() == 0) {
            return sql.toString();
        }
        sql.append(" ORDER BY ");
        for (Order order : orders) {
            sql.append(order.getProperties());
            if ("DB_DESC".equals(order.getDirection())) {
                sql.append(" DESC ");
            } else {
                sql.append(" ASC ");
            }
        }
        return sql.toString();
    }


    /**
     * 条件拼接sql
     *
     * @param conditions 条件集合
     * @return Where 1=1 后面的sql
     */
    public static String getConditionSql(List<Conditions> conditions) {

        StringBuffer sql = new StringBuffer();

        sql.append(" where 1 = 1 ");
        for (Conditions condition : conditions) {
            String paramCondition = condition.getCondition();

            String key = condition.getKey();
            sql.append(" AND ");
            sql.append(key);

            switch (paramCondition) {
                case "DB_GREATER_THAN":
                    sql.append(" > ");
                    sql.append(":").append(key);
                    break;
                case "DB_LESS_THAN":
                    sql.append(" < ");
                    sql.append(":").append(key);
                    break;
                case "DB_LIKE":
                    sql.append(" like ");
                    sql.append(":").append(key);
                    break;
                case "DB_GREATER_THAN_EQUAL":
                    sql.append(" >= ");
                    sql.append(":").append(key);
                    break;
                case "DB_LESS_THAN_EQUAL":
                    sql.append(" <= ? ");
                    break;
                case "DB_IN":
                    sql.append(" in ");
                    sql.append("(");
                    sql.append(":").append(key);
                    sql.append(")");
                    break;
                case "DB_NOT_EQUAL":
                    sql.append(" != ");
                    sql.append(":").append(key);
                    break;
                default:
                    sql.append(" = ");
                    sql.append(":").append(key);
                    break;
            }
        }
        return sql.toString();
    }

}
