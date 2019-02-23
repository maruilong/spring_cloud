package com.xinyuan.im.service;

import com.xinyuan.im.service.hystric.RedisHystric;
import com.xinyuan.redis.client.RedisClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shinian
 */
@FeignClient(value = "redis-service", fallback = RedisHystric.class)
public interface RedisService extends RedisClient {
}
