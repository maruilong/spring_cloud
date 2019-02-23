package com.xinyuan.data.client;

import com.xinyuan.data.model.BaseDataRemoveRequest;
import com.xinyuan.data.model.BaseDataSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liang
 */
public interface BaseDataOptClient {

    @RequestMapping("/api/baseData/save")
    ResponseEntity<Object> save(@RequestBody BaseDataSaveRequest saveRequest);

    @RequestMapping("/api/baseData/remove")
    ResponseEntity<Object> remove(@RequestBody BaseDataRemoveRequest removeRequest);
}
