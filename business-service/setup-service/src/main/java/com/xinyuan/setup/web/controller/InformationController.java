package com.xinyuan.setup.web.controller;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.Message;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.setup.entity.Information;
import com.xinyuan.setup.service.InformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author hzx
 * @date 2019/1/10 17:24
 */

@Api(description = "信息集")
@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;


    @ApiOperation(value = "保存", notes = "保存")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<Message> save(@RequestBody Information information) {
        informationService.save(information);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Message> delete(@RequestBody List<Long> ids) {
        informationService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "更新", notes = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Message> update(@RequestBody Information information) {
        informationService.update(information);
        return ResponseEntity.ok(ResultUtil.success());
    }


    @ApiOperation(value = "条件查询", notes = "条件查询")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<Page<Information>> query(@RequestBody PageBody pageBody) {
        Page<Information> page = null;
        try {
            page = informationService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }
}
