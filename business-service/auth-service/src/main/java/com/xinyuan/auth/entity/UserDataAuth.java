package com.xinyuan.auth.entity;

import io.swagger.annotations.ApiModelProperty;
import static javax.persistence.GenerationType.IDENTITY;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liang
 */
@Data
@Entity
@Table(name = "user_data_auth")
public class UserDataAuth {

    @Id
    @ApiModelProperty(value = "编号", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户编号", name = "userId", example = "1")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "信息集编码", name = "informationCode", example = "RY01")
    @Column(name = "information_code")
    private String informationCode;

    @ApiModelProperty(value = "栏目编码", name = "columnCode", example = "0")
    @Column(name = "column_code")
    private String columnCode;


}
