package com.xinyuan.im.service.hystric;

import com.xinyuan.im.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 断路器 当原来任务失败的时候执行
 *
 * @author liang
 */
@Slf4j
@Component
public class RedisHystric implements RedisService {

    @Override
    public ResponseEntity<Set<String>> getAllKeys() {
        log.error("----------------出错 " );
        return null;
    }

    @Override
    public void addToListRight(String listKey, String value) {

    }

    @Override
    public List<Object> getList(String listKey, long start, long end) {
        return null;
    }

    @Override
    public void remove(String key) {

    }
}
