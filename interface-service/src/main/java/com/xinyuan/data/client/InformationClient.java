package com.xinyuan.data.client;

import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.CodeTypeDTO;
import com.xinyuan.data.model.InformationDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface InformationClient {

    @RequestMapping(value = "/api/information/query", method = RequestMethod.POST)
    ResponseEntity<Object> query(@RequestBody PageBody pageBody);

    @RequestMapping(value ="/api/information/save", method = RequestMethod.POST)
    ResponseEntity<Object> save(@RequestBody InformationDTO informationDTO);

    @RequestMapping(value ="/api/information/remove", method = RequestMethod.POST)
    ResponseEntity<Object> delete(@RequestBody List<Long> ids );

    @RequestMapping(value ="/api/information/update", method = RequestMethod.POST)
    ResponseEntity<Object> update(@RequestBody InformationDTO informationDTO);

}
