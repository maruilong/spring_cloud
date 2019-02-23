package com.xinyuan.account.service;

import com.xinyuan.base.config.security.FeignHeaderInterceptor;
import com.xinyuan.data.client.InformationClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "setup-service", configuration = FeignHeaderInterceptor.class)
public interface InformationService extends InformationClient {
}
