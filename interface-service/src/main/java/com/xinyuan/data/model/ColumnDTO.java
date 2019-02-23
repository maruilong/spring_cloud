package com.xinyuan.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
@Data
public class ColumnDTO {

    private Long id;

    private String informationCode;

    private String code;

    private String group_id;

    private String col_name;

    private String col_value;

    private String default_order;

    private String is_many;

    private String any_node;

    private String pic_size;

    private String start_time;

    private String operator;

    private String operation_time;

    private String code_id;

    private String name;

    private Integer show;

    private Integer show_type;

    private Integer must;

    private Integer custom;

    private Integer encryption;

    private Integer type;

    private Integer showType;

    private Integer columnsLength;

    private Integer codeId;

    private String proving;

    private String defaultValue;

    private String formula;

    private Integer columnsWide;

    private String status;

    private String optDate;

}
