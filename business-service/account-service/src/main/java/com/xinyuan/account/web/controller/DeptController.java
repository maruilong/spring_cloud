package com.xinyuan.account.web.controller;

import com.xinyuan.account.service.BaseDataOptService;
import com.xinyuan.account.service.BaseDataQueryService;
import com.xinyuan.data.model.BaseDataQueryRequest;
import com.xinyuan.data.model.BaseDataRemoveRequest;
import com.xinyuan.data.model.BaseDataSaveRequest;
import com.xinyuan.data.model.QueryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyf
 */
@Api(description = "部门管理")
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private BaseDataQueryService baseDataQueryService;

    @Autowired
    private BaseDataOptService baseDataOptService;

    @ApiOperation(value = "查询", notes = "查询")
    @ApiImplicitParam(name = "queryRequest", value = "queryRequest", required = true, dataType = "BaseDataQueryRequest")
    @RequestMapping(value="query",method = RequestMethod.POST)
    public ResponseEntity<QueryResponse> query(@RequestBody BaseDataQueryRequest queryRequest) {
        return baseDataQueryService.query(queryRequest);
    }



    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value="save",method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody BaseDataSaveRequest saveRequest) {
        baseDataOptService.save(saveRequest);
        return ResponseEntity.ok("success");
    }


    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value="remove",method = RequestMethod.POST)
    public ResponseEntity<Object> remove(@RequestBody BaseDataRemoveRequest removeRequest) {
        baseDataOptService.remove(removeRequest);
        return ResponseEntity.ok("success");
    }


}
