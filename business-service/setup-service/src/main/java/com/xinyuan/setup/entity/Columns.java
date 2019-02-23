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
@Table(name = "columns")
public class Columns {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "代码编码", name = "information_code", example = "YR01")
    @Column(name = "information_code")
    private String informationCode;

    @ApiModelProperty(value = "字段编码", name = "code", example = "0")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "所属分组(为0则代表最上级)", name = "group_id", example = "0")
    @Column(name = "group_id")
    private String group_id;

    @ApiModelProperty(value = "栏目名称", name = "col_name", example = "0")
    @Column(name = "col_name")
    private String col_name;


    @ApiModelProperty(value = "代码索引", name = "col_value", example = "0")
    @Column(name = "col_value")
    private String col_value;

    @ApiModelProperty(value = "默认排序", name = "default_order", example = "0")
    @Column(name = "default_order")
    private String default_order;

    @ApiModelProperty(value = "代码是否可多选(0 单选 1 多选)", name = "is_many", example = "0")
    @Column(name = "is_many")
    private String is_many;

    @ApiModelProperty(value = "代码是否支持任意节点", name = "any_node", example = "0")
    @Column(name = "any_node")
    private String any_node;

    @ApiModelProperty(value = "图片尺寸", name = "pic_size", example = "0")
    @Column(name = "pic_size")
    private String pic_size;

    @ApiModelProperty(value = "多记录信息集起效时间", name = "start_time", example = "0")
    @Column(name = "start_time")
    private String start_time;

    @ApiModelProperty(value = "操作人id", name = "operator", example = "0")
    @Column(name = "operator")
    private String operator;

    @ApiModelProperty(value = "操作时间", name = "operation_time", example = "0")
    @Column(name = "operation_time")
    private String operation_time;

    @ApiModelProperty(value = "", name = "code_id", example = "0")
    @Column(name = "code_id",insertable = false,updatable = false)
    private String code_id;


    @ApiModelProperty(value = "字段中文名", name = "name", example = "0")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "是否显示(0不显示 1显示)", name = "show", example = "0")
    @Column(name = "show")
    private Integer show;

    @ApiModelProperty(value = "", name = "show_type", example = "0")
    @Column(name = "show_type",insertable = false,updatable = false)
    private Integer show_type;


    @ApiModelProperty(value = "是否必填(0否 1是)", name = "must", example = "0")
    @Column(name = "must")
    private Integer must;

    @ApiModelProperty(value = "是否自定义(0不是,1是)", name = "custom", example = "0")
    @Column(name = "custom")
    private Integer custom;

    @ApiModelProperty(value = "是否加密(0否 1是)", name = "encryption", example = "0")
    @Column(name = "encryption")
    private Integer encryption;

    @ApiModelProperty(value = "字段类型(字符,整数,代码,日期)", name = "type", example = "1")
    @Column(name = "type")
    private Integer type;

    @ApiModelProperty(value = "显示方式(文本 列表 树)", name = "type", example = "1")
    @Column(name = "show_type")
    private Integer showType;

    @ApiModelProperty(value = "字段长度", name = "columnsLength", example = "255")
    @Column(name = "columns_length")
    private Integer columnsLength;

    @ApiModelProperty(value = "所属类型ID", name = "codeId", example = "1")
    @Column(name = "code_id")
    private Integer codeId;

    @ApiModelProperty(value = "验证函数", name = "proving", example = "1")
    @Column(name = "proving")
    private String proving;

    @ApiModelProperty(value = "默认值", name = "defaultValue", example = "1")
    @Column(name = "default_value")
    private String defaultValue;

    @ApiModelProperty(value = "是否审批(0未审批 1审批)", name = "formula", example = "1")
    @Column(name = "formula")
    private String formula;

    @ApiModelProperty(value = "字段长度", name = "columnsWide", example = "255")
    @Column(name = "columns_wide")
    private Integer columnsWide;

    @ApiModelProperty(value = "状态标识(0是停用、1是启用、2是删除)", name = "status", example = "0")
    @Column(name = "status")
    private String status;

    @ApiModelProperty(value = "操作时间", name = "optDate", example = "2019-01-10 17:50:00")
    @Column(name = "opt_date")
    private String optDate;
}
