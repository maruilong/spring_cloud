package com.xinyuan.account.service;

import com.xinyuan.data.client.BaseDataQueryClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 基础数据查询服务
 *
 * @author liang
 */
@FeignClient(value = "data-service")
public interface BaseDataQueryService extends BaseDataQueryClient {
}
