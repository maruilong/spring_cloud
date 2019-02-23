package com.xinyuan.setup.web.controller;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.Message;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.setup.entity.Code;
import com.xinyuan.setup.service.CodeService;
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
 * @Description: 代码
 * @author hzx
 * @date 2019/1/10 17:24
 */

@Api(description = "代码")
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;


    @ApiOperation(value = "保存", notes = "保存")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<Message> save(@RequestBody Code code) {
        codeService.save(code);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Message> delete(@RequestBody List<Long> ids) {
        codeService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "更新", notes = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Message> update(@RequestBody Code code) {
        codeService.update(code);
        return ResponseEntity.ok(ResultUtil.success());
    }


    @ApiOperation(value = "条件查询", notes = "条件查询")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<Page<Code>> query(@RequestBody PageBody pageBody) {
        Page<Code> page = null;
        try {
            page = codeService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }
}
