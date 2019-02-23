package com.xinyuan.setup.entity;

import com.xinyuan.base.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author hzx
 * @Description: 信息集
 * @date 2019/1/10 17:38
 */

@Data
@Entity
@Table(name = "information")
public class Information extends BaseEntity {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long fid;


    @ApiModelProperty(value = "信息集编码", name = "code", example = "0")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "分类（1信息集分类  2信息集）", name = "classify", example = "0")
    @Column(name = "classify")
    private String classify;

    @ApiModelProperty(value = "信息集编码（唯一）", name = "coding", example = "0")
    @Column(name = "coding")
    private String coding;


    @ApiModelProperty(value = "默认排序", name = "default_sort", example = "0")
    @Column(name = "default_sort")
    private String default_sort;

    @ApiModelProperty(value = "名字", name = "name", example = "0")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "顺序", name = "orders", example = "0")
    @Column(name = "orders")
    private String orders;

    @ApiModelProperty(value = "信息集分类代码", name = "pcoding", example = "0")
    @Column(name = "pcoding")
    private String pcoding;


    @ApiModelProperty(value = "是否末级节点", name = "last_stage", example = "0")
    @Column(name = "last_stage")
    private Integer last_stage;


    @ApiModelProperty(value = "信息集类型", name = "type", example = "0")
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "所属行业", name = "industry", example = "0")
    @Column(name = "industry")
    private String industry;

    @ApiModelProperty(value = "显示方式", name = "show_type", example = "0")
    @Column(name = "show_type")
    private String show_type;


    @ApiModelProperty(value = "操作人", name = "Operator", example = "0")
    @Column(name = "operator")
    private String operator;

    @ApiModelProperty(value = "操作sj", name = "Operation_time", example = "0")
    @Column(name = "operation_time")
    private String operation_time;

    @ApiModelProperty(value = "删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted;



    @ApiModelProperty(value = "是否显示(0不显示,1显示)", name = "show", example = "0")
    @Column(name = "show")
    private String show;

    @ApiModelProperty(value = "显示顺序", name = "displayOrder", example = "0")
    @Column(name = "display_order")
    private Integer displayOrder;

    @ApiModelProperty(value = "父信息集编码", name = "pId", example = "0")
    @Column(name = "p_id")
    private Long pId;

    @ApiModelProperty(value = "是否多记录(0不是,1是)", name = "multiple", example = "0")
    @Column(name = "multiple")
    private String multiple;

    @ApiModelProperty(value = "是否有子集", name = "child", example = "0")
    @Column(name = "child")
    private String child;

    @ApiModelProperty(value = "状态标识(0是停用、1是启用、2是删除)", name = "status", example = "0")
    @Column(name = "status")
    private String status;

    @ApiModelProperty(value = "操作时间", name = "optDate", example = "2019-01-10 17:50:00")
    @Column(name = "opt_date")
    private String optDate;
}
