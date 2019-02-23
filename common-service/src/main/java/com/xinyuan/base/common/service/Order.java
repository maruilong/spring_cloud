package com.xinyuan.base.common.service;


import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: hwz
 * @Date: Created in 10:43 2018/4/24.
 */
public class Order {

    @ApiModelProperty(value = "正序ASC倒序DESC", name = "ASC")
    public String direction;

    @ApiModelProperty(value = "排序的字段", name = "id")
    public String properties;

    public Order(String direction, String properties) {
        super();
        this.direction = direction;
        this.properties = properties;
    }

    public Order() {
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
