package com.xinyuan.account.service;

import com.xinyuan.data.client.CodeTypeClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "setup-service")
public interface CodeTypeService extends CodeTypeClient {
}
