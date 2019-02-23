package com.xinyuan.data.model;

import lombok.Data;

/**
 * @author liang
 */
@Data
public class BaseDataRemoveRequest {

    /**
     * 单记录是ID 多记录是PK_ID
     */
    private String id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 起效
     */
    private String startTime;

    /**
     * 是否是多记录
     */
    private boolean isMultiple;
}
