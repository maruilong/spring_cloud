package com.xinyuan.log.entity;

import com.xinyuan.base.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Description: 用户日志
 * @author hzx
 * @date 2019/1/10 16:59
 */
@Data
@Entity
@Table(name = "user_log")
public class UserLog extends BaseEntity {

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

    @ApiModelProperty(value = "内容", name = "content", example = "0")
    @Column(name = "content")
    private String content;

}
