package com.xinyuan.data.client;

import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.ColumnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ColumnClient {

    @RequestMapping(value = "/api/column/query", method = RequestMethod.POST)
    ResponseEntity<Object> query(@RequestBody PageBody pageBody);

    @RequestMapping(value ="/api/column/save", method = RequestMethod.POST)
    ResponseEntity<Object> save(@RequestBody ColumnDTO codeDTO);

    @RequestMapping(value ="/api/column/remove", method = RequestMethod.POST)
    ResponseEntity<Object> delete(@RequestBody List<Long> ids );

    @RequestMapping(value ="/api/column/update", method = RequestMethod.POST)
    ResponseEntity<Object> update(@RequestBody ColumnDTO codeDTO);

}
