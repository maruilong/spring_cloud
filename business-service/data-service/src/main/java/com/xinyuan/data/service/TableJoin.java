package com.xinyuan.data.service;

import com.xinyuan.base.common.service.Order;
import com.xinyuan.base.common.web.Conditions;
import com.xinyuan.data.model.TableQuery;
import com.xinyuan.data.model.BaseDataQueryRequest;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql组成部分为WITH + 主表 + ,(其他子表) + 最后的查询 +一堆left join on + 最后的查询条件拼接和拍排序拼接
 *
 * @author liang
 */
public class TableJoin {

    /**
     * 连接时候的主表名称
     */
    private String primaryTableName;

    /**
     * 子表格 可能有很多个
     */
    private List<TableQuery> tableQueries;

    /**
     * 条件集
     */
    private List<Conditions> conditions;

    /**
     * 排序集合
     */
    private List<Order> orders;

    /**
     * sqlMqp
     */
    private Map<String, String> sqlMap = new HashMap<>();

    /**
     * 所有单表的
     */
    private Map<String, TableQuery> singleTableMap = new HashMap<>();

    /**
     * 所有多表格的
     */
    private Map<String, TableQuery> multipleTableMap = new HashMap<>();

    /**
     * 所有单表的栏目
     */
    private List<String> singleColumns = new ArrayList<>();

    /**
     * 所有多表栏目
     */
    private List<String> multipleColumns = new ArrayList<>();

    /**
     * 构造方面传入你需要连表查询的语句
     * 第一个传入的sql设置为主表
     *
     * @param queryRequest sql 拼接对象
     */
    public TableJoin(BaseDataQueryRequest queryRequest) {
        this.primaryTableName = queryRequest.getPrimaryTableName();
        this.tableQueries = queryRequest.getTables();
        this.conditions = queryRequest.getConditions();
        this.orders = queryRequest.getOrders();
        for (TableQuery tableQuery : tableQueries) {

            String tableName = tableQuery.getTableName();

            if (tableQuery.isMultiple()) {
                multipleTableMap.put(tableName, tableQuery);
                multipleColumns.addAll(tableQuery.getColumns());
            } else {
                singleTableMap.put(tableName, tableQuery);
                singleColumns.addAll(tableQuery.getColumns());
            }
        }
    }

    /**
     * 表链接查询
     * 按照传入的顺序 第一个为主表 其他的都是子表
     * 一定是按照SERIALID连接的
     * 第一个传进来的当主表用
     */
    public String getQuerySql() {

        StringBuffer sql = new StringBuffer();
        if (StringUtils.isNotEmpty(primaryTableName)) {
            //先拿到所有单表
            //然后把单表全部拼接
            sql.append(" WITH ");
            sql.append("single_").append(primaryTableName);
            sql.append(" AS (");
            sql.append(getSingleSql());
            sql.append(" ) ");
            sql.append(getMultipleSql());

            //最后的查询语句
            sql.append(" SELECT ");
            List<String> columns = new ArrayList<>(singleColumns);
            columns.addAll(multipleColumns);
            for (String column : columns) {
                sql.append(column);
                sql.append(",");
            }
            sql.append("single_").append(primaryTableName);
            sql.append(".serial_id ");
            sql.append(" FROM ");
            sql.append("single_").append(primaryTableName);

            sql.append(leftJoin(new ArrayList<>(multipleTableMap.keySet())));

            sql.append(SqlStitching.getConditionSql(conditions));
            sql.append(SqlStitching.getOrderSql(orders));

        } else {
            for (TableQuery tableQuery : tableQueries) {
                String orderColumn = tableQuery.getOrderColumn();
                boolean multiple = tableQuery.isMultiple();
                String tableName = tableQuery.getTableName();
                String dataQuerySql = SqlStitching.dataQuerySql(tableName, multiple);
                List<String> columns = tableQuery.getColumns();
                dataQuerySql = SqlStitching.filter(dataQuerySql, multiple);
                dataQuerySql = SqlStitching.trans(dataQuerySql, columns, multiple);
                if (multiple) {
                    dataQuerySql = SqlStitching.multipleOrder(dataQuerySql, orderColumn, columns);
                }
                dataQuerySql = SqlStitching.getSql(dataQuerySql, columns, multiple);

                sql.append(dataQuerySql);
                sql.append(SqlStitching.getConditionSql(conditions));
                sql.append(SqlStitching.getOrderSql(orders));
            }
        }
        return sql.toString();
    }

    /**
     * 表链接查询
     * 按照传入的顺序 第一个为主表 其他的都是子表
     * 一定是按照SERIALID连接的
     * 第一个传进来的当主表用
     */
    public String getCountSql() {

        StringBuffer sql = new StringBuffer();
        if (StringUtils.isNotEmpty(primaryTableName)) {
            //先拿到所有单表
            //然后把单表全部拼接
            sql.append(" WITH ");
            sql.append("single_").append(primaryTableName);
            sql.append(" AS (");
            sql.append(getSingleSql());
            sql.append(" ) ");
            sql.append(getMultipleSql());

            //最后的查询语句
            sql.append(" SELECT ");
            List<String> columns = new ArrayList<>(singleColumns);
            columns.addAll(multipleColumns);
            sql.append(" count(*) ");
            sql.append(" FROM ");
            sql.append("single_").append(primaryTableName);

            sql.append(leftJoin(new ArrayList<>(multipleTableMap.keySet())));

            sql.append(SqlStitching.getConditionSql(conditions));

        } else {
            for (TableQuery tableQuery : tableQueries) {
                String orderColumn = tableQuery.getOrderColumn();
                boolean multiple = tableQuery.isMultiple();
                String tableName = tableQuery.getTableName();
                String dataQuerySql = SqlStitching.dataQuerySql(tableName, multiple);
                List<String> columns = tableQuery.getColumns();
                dataQuerySql = SqlStitching.filter(dataQuerySql, multiple);
                dataQuerySql = SqlStitching.trans(dataQuerySql, columns, multiple);
                if (multiple) {
                    dataQuerySql = SqlStitching.multipleOrder(dataQuerySql, orderColumn, columns);
                }
                dataQuerySql = SqlStitching.getCountSql(dataQuerySql);

                sql.append(dataQuerySql);
                sql.append(SqlStitching.getConditionSql(conditions));
            }
        }
        return sql.toString();
    }

    /**
     * 获得多记录查询到最后的sql
     *
     * @return
     */
    public String getMultipleSql() {
        StringBuffer sql = new StringBuffer();
        //下面是多表的
        for (String tableName : multipleTableMap.keySet()) {
            //多表连接 多记录前面肯定有个逗号
            sql.append(" , ");
            sql.append(" multiple_");
            sql.append(tableName);
            sql.append(" AS ( ");
            TableQuery tableQuery = multipleTableMap.get(tableName);
            boolean multiple = tableQuery.isMultiple();
            List<String> columns = tableQuery.getColumns();

            String dataQuerySql = SqlStitching.dataQuerySql(tableName, multiple);

            //这里的肯定是false
            dataQuerySql = SqlStitching.filter(dataQuerySql, true);
            //这里的columns 应该是所有单记录的了 肯定也是单记录
            dataQuerySql = SqlStitching.trans(dataQuerySql, columns, true);
            //排序
            dataQuerySql = SqlStitching.multipleOrder(dataQuerySql, tableQuery.getOrderColumn(), columns);
            //查询出最终的sql
            dataQuerySql = SqlStitching.getSql(dataQuerySql, columns, true);

            sql.append(dataQuerySql);
            sql.append(" ) ");
        }

        return sql.toString();
    }

    /**
     * 获得单记录查询到最后的sql
     *
     * @return
     */
    public String getSingleSql() {

        TableQuery primaryTable = singleTableMap.get(primaryTableName);
        String primaryDataQuerySql = SqlStitching.dataQuerySql(primaryTableName, primaryTable.isMultiple());

        for (String tableName : singleTableMap.keySet()) {
            if (!primaryTableName.equals(tableName)) {
                TableQuery tableQuery = singleTableMap.get(tableName);
                SqlStitching sqlStitching = new SqlStitching(tableQuery);
                String dataQuerySql = sqlStitching.dataQuerySql(tableName, tableQuery.isMultiple());
                primaryDataQuerySql += " UNION ALL  ";
                primaryDataQuerySql += dataQuerySql;
            }
        }

        //这里的肯定是false
        primaryDataQuerySql = SqlStitching.filter(primaryDataQuerySql, false);
        //这里的columns 应该是所有单记录的了 肯定也是单记录
        primaryDataQuerySql = SqlStitching.trans(primaryDataQuerySql, singleColumns, false);
        //查询出最终的sql
        primaryDataQuerySql = SqlStitching.getSql(primaryDataQuerySql, singleColumns, false);

        return primaryDataQuerySql;
    }


    /**
     * 拼接join语句
     *
     * @return
     */
    private String leftJoin(List<String> tableNames) {
        StringBuffer sql = new StringBuffer();
        for (String tableName : tableNames) {
            sql.append(" LEFT JOIN ");
            sql.append(" multiple_");
            sql.append(tableName);
            sql.append(" ON ");
            sql.append(" ( ");
            sql.append(" single_");
            sql.append(primaryTableName);
            sql.append(".serial_id");
            sql.append(" = ");
            sql.append(" multiple_");
            sql.append(tableName);
            sql.append(".serial_id");
            sql.append(" ) ");
        }

        return sql.toString();
    }
}
