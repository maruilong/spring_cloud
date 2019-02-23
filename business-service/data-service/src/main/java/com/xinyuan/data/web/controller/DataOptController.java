package com.xinyuan.data.web.controller;

import com.xinyuan.data.client.BaseDataOptClient;
import com.xinyuan.data.model.BaseDataRemoveRequest;
import com.xinyuan.data.model.BaseDataSaveRequest;
import com.xinyuan.data.service.BaseDataOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang
 */
@RestController
public class DataOptController implements BaseDataOptClient {

    @Autowired
    private BaseDataOptService optService;

    public ResponseEntity<Object> save(@RequestBody BaseDataSaveRequest saveRequest) {
        optService.save(saveRequest);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<Object> remove(@RequestBody BaseDataRemoveRequest removeRequest) {
        optService.remove(removeRequest);
        return ResponseEntity.ok("success");
    }

}
