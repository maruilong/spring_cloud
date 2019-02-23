package com.xinyuan.setup.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author hzx
 * @Description: 代码类型
 * @date 2019/1/10 17:41
 */

@Data
@Entity
@Table(name = "code_type")
public class CodeType {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long fid;

    @ApiModelProperty(value = "类型名称", name = "name", example = "0")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "代码分类编码", name = "coding", example = "0")
    @Column(name = "coding")
    private String coding;


    @ApiModelProperty(value = "父类代码分类编码", name = "pcoding", example = "0")
    @Column(name = "pcoding")
    private String pcoding;

    @ApiModelProperty(value = "代码类型", name = "type", example = "0")
    @Column(name = "type")
    private Long type;

    @ApiModelProperty(value = "排序", name = "sort", example = "0")
    @Column(name = "sort")
    private Integer sort;


    @ApiModelProperty(value = "末级节点", name = "last_stage", example = "0")
    @Column(name = "last_stage")
    private Integer last_stage;

    @ApiModelProperty(value = "", name = "user_defined", example = "0")
    @Column(name = "user_defined")
    private Integer user_defined;



    @ApiModelProperty(value = "父类型编号", name = "pId", example = "0")
    @Column(name = "p_id")
    private Long pId;

    @ApiModelProperty(value = "显示顺序", name = "displayOrder", example = "0")
    @Column(name = "display_order")
    private Integer displayOrder;

    @ApiModelProperty(value = "状态标识(0是停用、1是启用、2是删除)", name = "status", example = "0")
    @Column(name = "status")
    private String status;

    @ApiModelProperty(value = "是否多层级(1是列表显示、2是多级树形显示)", name = "multilevel", example = "0")
    @Column(name = "multilevel")
    private String multilevel;

    @ApiModelProperty(value = "操作时间", name = "optDate", example = "2019-01-10 17:50:00")
    @Column(name = "opt_date")
    private String optDate;

    @ApiModelProperty(value = "是否自定义(0系统,1自定义)", name = "custom", example = "0")
    @Column(name = "custom")
    private String custom;
}
