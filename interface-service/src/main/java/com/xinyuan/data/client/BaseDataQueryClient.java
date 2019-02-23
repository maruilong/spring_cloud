package com.xinyuan.data.client;

import com.xinyuan.data.model.BaseDataQueryRequest;
import com.xinyuan.data.model.QueryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liang
 */

public interface BaseDataQueryClient {

    @RequestMapping(value = "/api/baseData/query", name = "query", method = RequestMethod.POST)
    ResponseEntity<QueryResponse> query(@RequestBody BaseDataQueryRequest queryRequest);
}
