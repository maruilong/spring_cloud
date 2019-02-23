package com.xinyuan.redis.web.rpc;

import com.xinyuan.redis.client.RedisClient;
import com.xinyuan.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @Author: hwz
 * @Date: Created in 17:27 2018/12/17.
 */
@RefreshScope
@RestController
public class RedisApi implements RedisClient {

    @Autowired
    private RedisService redisService;

    @Override
    public ResponseEntity<Set<String>> getAllKeys() {
        Set<String> allKeys = redisService.getAllKeys();
        return ResponseEntity.ok(allKeys);
    }

    @Override
    public void addToListRight(String listKey, String value) {
        redisService.addToListRight(listKey, value);
    }

    @Override
    public List<Object> getList(String listKey, long start, long end) {
       return redisService.getList(listKey, start, end);
    }

    @Override
    public void remove(String key) {
        redisService.remove(key);
    }


}
