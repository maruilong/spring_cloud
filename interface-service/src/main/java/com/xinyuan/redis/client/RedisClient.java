package com.xinyuan.redis.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @Author: hwz
 * @Date: Created in 17:22 2018/12/17.
 */
public interface RedisClient {

    @RequestMapping(value = "/api/redis/getAllKeys", method = RequestMethod.POST)
    ResponseEntity<Set<String>> getAllKeys();


    @RequestMapping(value = "/api/redis/addToListRight", method = RequestMethod.POST)
    void addToListRight(@RequestParam("listKey") String listKey,@RequestParam("value") String value);

    @RequestMapping(value = "/api/redis/getList", method = RequestMethod.POST)
    List<Object> getList(@RequestParam("listKey") String listKey, @RequestParam("start") long start, @RequestParam("end") long end);

    @RequestMapping(value = "/api/redis/delete", method = RequestMethod.POST)
    void remove(@RequestParam("key") String key);
}
