package com.xinyuan.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Data
public class CodeDTO {

    private Long cid;

    private String classification;


    private String coding;


    private String name;

    private String pcode;

    private String sort;


    private String code;

    private String value;

    private String content;

    private String cotyledon;

    private Integer displayOrder;

    private String show;

    private String status;

    private Long pId;

    private String operate_time;

    private Integer last_stage;

    private Integer operator;


    private String display_order;


    private String opt_date;



}
