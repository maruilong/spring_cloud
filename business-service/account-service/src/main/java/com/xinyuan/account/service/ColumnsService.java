package com.xinyuan.account.service;

import com.xinyuan.data.client.ColumnClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "setup-service")
public interface ColumnsService extends ColumnClient {
}
