package com.xinyuan.base.common.web;

import lombok.Data;

/**
 * @author liang
 */
@Data
public class Param {

    private String key;

    /**
     * 数据起效时间
     */
    private String startTime;

    private String value;

    private Integer status;

    /**
     * 外键
     */
    private String pkId;

    public Param() {
    }

    public Param(String key, String startTime, String value) {
        this.key = key;
        this.startTime = startTime;
        this.value = value;
    }

    public Param(String key, String startTime, String value, Integer status) {
        this.key = key;
        this.startTime = startTime;
        this.value = value;
        this.status = status;
    }

}
