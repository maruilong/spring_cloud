package com.xinyuan.account.service;

import com.xinyuan.data.client.BaseDataOptClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 基础数据操作服务
 * @author liang
 */
@FeignClient(value = "data-service")
public interface BaseDataOptService extends BaseDataOptClient {
}
