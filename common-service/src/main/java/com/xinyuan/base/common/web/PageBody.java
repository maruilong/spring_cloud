package com.xinyuan.base.common.web;

import com.xinyuan.base.common.service.Order;
import com.xinyuan.base.common.service.PageBean;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzx
 * @Description:
 * @date 2018/4/917:48
 */
@Data
public class PageBody {

    private PageBean pageBean=new PageBean();

    private List<Conditions> conditions=new ArrayList<>();

    private List<Order> orders=new ArrayList<>();
}
