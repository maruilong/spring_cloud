package com.xinyuan.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
@Data
public class CodeTypeDTO {

    private Long fid;

    private String name;

    private String coding;

    private String pcoding;

    private Long type;

    private Integer sort;

    private Integer last_stage;

    private Integer user_defined;
    private Long pId;

    private Integer displayOrder;

    private String status;

    private String multilevel;

    private String optDate;

    private String custom;
}
