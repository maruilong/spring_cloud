package com.xinyuan.account.service;

import com.xinyuan.account.service.hystric.CodeHystric;
import com.xinyuan.data.client.CodeClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 代码
 */
@FeignClient(value = "setup-service",fallback = CodeHystric.class)
public interface CodeService extends CodeClient {
}
