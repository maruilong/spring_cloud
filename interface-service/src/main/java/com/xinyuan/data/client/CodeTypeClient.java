package com.xinyuan.data.client;

import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.CodeTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CodeTypeClient {

    @RequestMapping(value = "/api/codeType/query", method = RequestMethod.POST)
    ResponseEntity<Object> query(@RequestBody PageBody pageBody);

    @RequestMapping(value ="/api/codeType/save", method = RequestMethod.POST)
    ResponseEntity<Object> save(@RequestBody CodeTypeDTO codeDTO);

    @RequestMapping(value ="/api/codeType/remove", method = RequestMethod.POST)
    ResponseEntity<Object> delete(@RequestBody List<Long> ids );

    @RequestMapping(value ="/api/codeType/update", method = RequestMethod.POST)
    ResponseEntity<Object> update(@RequestBody CodeTypeDTO codeDTO);

}
