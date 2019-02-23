package com.xinyuan.data.model;

import lombok.Data;

import java.util.List;

/**
 * @author liang
 */
@Data
public class TableQuery {

    private String tableName;

    /**
     * 用于多记录的排序
     */
    private String orderColumn;

    /**
     * 查询的列表
     */
    private List<String> columns;

    /**
     * 是否是多记录
     */
    private boolean isMultiple;


    /**
     *
     */
    public TableQuery() {
    }

    /**
     * 单记录单表查询
     *
     * @param tableName 表名
     * @param columns   栏目
     */
    public TableQuery(String tableName, List<String> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    /**
     * 多记录单表查询
     *
     * @param tableName   表名
     * @param orderColumn 排序字段
     * @param columns     栏目
     */
    public TableQuery(String tableName, String orderColumn, List<String> columns) {
        this.tableName = tableName;
        this.orderColumn = orderColumn;
        this.columns = columns;
    }

}
