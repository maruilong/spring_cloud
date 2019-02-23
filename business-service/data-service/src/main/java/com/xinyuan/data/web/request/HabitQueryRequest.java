package com.xinyuan.data.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liang
 */
@Data
@ApiModel
public class HabitQueryRequest {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "functionId", value = "方法ID")
    private Long functionId;
}
