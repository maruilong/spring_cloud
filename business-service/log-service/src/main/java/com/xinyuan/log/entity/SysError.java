package com.xinyuan.log.entity;

import com.xinyuan.base.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author hzx
 * @Description: 系统日常
 * @date 2019/1/10 17:12
 */
@Data
@Entity
@Table(name = "sys_error")
public class SysError extends BaseEntity {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "执行的用户编号", name = "userId", example = "0")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "执行时间", name = "optDate", example = "0")
    @Column(name = "opt_date")
    private String optDate;

    @ApiModelProperty(value = "异常内容", name = "content", example = "0")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "异常类名称", name = "className", example = "0")
    @Column(name = "class_name")
    private String className;

    @ApiModelProperty(value = "异常方法名", name = "methodName", example = "0")
    @Column(name = "method_name")
    private String methodName;

    @ApiModelProperty(value = "是否处理过(0没有 1处理过)", name = "handle", example = "0")
    @Column(name = "handle")
    private String handle;
}
