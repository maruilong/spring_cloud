package com.xinyuan.base.common.entity.enums;

/**
 * @author liang
 */
public enum Status {

    DISABLED(0, "已禁用"),

    ENABLE(1, "已启用"),

    DELETED(2, "已删除");

    private Integer id;

    private String value;

    Status(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
