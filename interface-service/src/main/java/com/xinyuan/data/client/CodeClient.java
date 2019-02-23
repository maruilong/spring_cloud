package com.xinyuan.data.client;

import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.CodeDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CodeClient {
    @RequestMapping(value = "/api/code/query", method = RequestMethod.POST)
    ResponseEntity<Object> query(@RequestBody PageBody pageBody);

    @RequestMapping(value ="/api/code/save", method = RequestMethod.POST)
    ResponseEntity<Object> save(@RequestBody CodeDTO codeDTO);

    @RequestMapping(value ="/api/code/remove", method = RequestMethod.POST)
    ResponseEntity<Object> delete(@RequestBody List<Long> ids );

    @RequestMapping(value ="/api/code/update", method = RequestMethod.POST)
    ResponseEntity<Object> update(@RequestBody CodeDTO codeDTO);

}
