package com.xinyuan.data.web.controller;

import com.xinyuan.data.client.BaseDataQueryClient;
import com.xinyuan.data.model.BaseDataQueryRequest;
import com.xinyuan.data.model.QueryResponse;
import com.xinyuan.data.service.BaseDataQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang
 */
@Api(description = "基础数据查询")
@RestController
public class BaseDataQueryController implements BaseDataQueryClient {

    @Autowired
    private BaseDataQueryService baseDataQueryService;

    @ApiImplicitParam(name = "queryRequest", value = "queryRequest", required = true, dataType = "BaseDataQueryRequest")
    public ResponseEntity<QueryResponse> query(@RequestBody BaseDataQueryRequest queryRequest) {
        return ResponseEntity.ok(baseDataQueryService.query(queryRequest));
    }
}
