package com.xinyuan.base.common.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: hwz
 * @Date: Created in 11:59 2018/4/26.
 */
@Data
public class Page {

    @ApiModelProperty(value = "当前页", name = "page")
    private Integer page;

    @ApiModelProperty(value = "一页的数据", name = "rows")
    private Integer rows;
}
