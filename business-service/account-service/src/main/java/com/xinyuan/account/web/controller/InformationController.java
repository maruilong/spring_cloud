package com.xinyuan.account.web.controller;

import com.xinyuan.account.service.InformationService;
import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.InformationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息集
 */
@Api(description = "信息集")
@RestController
@RequestMapping("information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @ApiOperation(value = "查询", notes = "查询")
    @RequestMapping(value="query",method = RequestMethod.POST)
    public ResponseEntity<Object> query(@RequestBody PageBody pageBody) {
        return informationService.query(pageBody);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody InformationDTO informationDTO) {
        informationService.save(informationDTO);
        return ResponseEntity.ok(ResultUtil.success());
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value="remove",method = RequestMethod.POST)
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        informationService.delete(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }
    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value="update",method = RequestMethod.POST)
    public ResponseEntity<Object> update(@RequestBody InformationDTO informationDTO) {
        informationService.update(informationDTO);
        return ResponseEntity.ok(ResultUtil.success());
    }



}
