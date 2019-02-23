package com.xinyuan.base.common.service;

/**
 * 参数条件
 *
 * @author
 * @since 2018-03-13
 */
public enum ParamCondition {

    /**
     * 等于
     */
    DB_EQUAL,
    /**
     * 大于
     */
    DB_GREATER_THAN,
    /**
     * 小于
     */
    DB_LESS_THAN,
    /**
     * 模糊查询
     */
    DB_LIKE,
    /**
     * 大于等于
     */
    DB_GREATER_THAN_EQUAL,
    /**
     * 小于等于
     */
    DB_LESS_THAN_EQUAL,
    /**
     * in
     */
    DB_IN,
    /**
     * 不等于
     */
    DB_NOT_EQUAL
}
