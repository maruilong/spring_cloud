package com.xinyuan.setup.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author hzx
 * @Description: 代码
 * @date 2019/1/10 17:40
 */
@Data
@Entity
@Table(name = "code")
public class Code {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long cid;

    @ApiModelProperty(value = "代码分类编码(代码索引)", name = "classification", example = "0")
    @Column(name = "classification")
    private String classification;


    @ApiModelProperty(value = "代码编码", name = "coding", example = "0")
    @Column(name = "coding")
    private String coding;


    @ApiModelProperty(value = "代码名称", name = "name", example = "0")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "父代码编码", name = "pcode", example = "0")
    @Column(name = "pcode")
    private String pcode;

    @ApiModelProperty(value = "排序", name = "sort", example = "0")
    @Column(name = "sort")
    private String sort;


    @ApiModelProperty(value = "代码编码", name = "code", example = "0")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "代码值", name = "value", example = "0")
    @Column(name = "value")
    private String value;

    @ApiModelProperty(value = "显示内容", name = "content", example = "0")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "是否子叶节点", name = "cotyledon", example = "0")
    @Column(name = "cotyledon")
    private String cotyledon;

    @ApiModelProperty(value = "显示顺序", name = "displayOrder", example = "0")
    @Column(name = "display_order",insertable = false,updatable = false)
    private Integer displayOrder;

    @ApiModelProperty(value = "是否显示(0不显示,1显示)", name = "show", example = "0")
    @Column(name = "show")
    private String show;

    @ApiModelProperty(value = "状态标识(0是停用、1是启用、2是删除)", name = "status", example = "0")
    @Column(name = "status")
    private String status;

    @ApiModelProperty(value = "父代码编号", name = "pId", example = "0")
    @Column(name = "p_id")
    private Long pId;

    @ApiModelProperty(value = "操作时间", name = "operate_time", example = "2019-01-10 17:50:00")
    @Column(name = "operate_time")
    private String operate_time;

    @ApiModelProperty(value = "末级节点", name = "last_stage", example = "0")
    @Column(name = "last_stage")
    private Integer last_stage;

    @ApiModelProperty(value = "末级节点", name = "operator", example = "0")
    @Column(name = "operator")
    private Integer operator;


    @ApiModelProperty(value = "", name = "display_order", example = "0")
    @Column(name = "display_order")
    private String display_order;


    @ApiModelProperty(value = "", name = "opt_date", example = "0")
    @Column(name = "opt_date")
    private String opt_date;




}
