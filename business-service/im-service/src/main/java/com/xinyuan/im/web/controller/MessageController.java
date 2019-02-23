package com.xinyuan.im.web.controller;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.Message;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.im.entity.ImMessage;
import com.xinyuan.im.service.ImMessageService;
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
 * @author liang
 */
@Api(description = "消息")
@RestController
@RequestMapping("/message")
public class MessageController {

        @Autowired
        private ImMessageService imMessageService;

        @ApiOperation(value = "保存", notes = "保存")
        @RequestMapping(value = "save", method = RequestMethod.POST)
        public ResponseEntity<Message> save(@RequestBody ImMessage imMessage) {
           imMessageService.save(imMessage);
            return ResponseEntity.ok(ResultUtil.success());
        }

        @ApiOperation(value = "删除", notes = "删除")
        @RequestMapping(value = "delete", method = RequestMethod.POST)
        public ResponseEntity<Message> delete(@RequestBody List<Long> ids) {
            imMessageService.removeList(ids);
            return ResponseEntity.ok(ResultUtil.success());
        }

        @ApiOperation(value = "更新", notes = "更新")
        @RequestMapping(value = "update", method = RequestMethod.POST)
        public ResponseEntity<Message> update(@RequestBody ImMessage message) {
            imMessageService.update(message);
            return ResponseEntity.ok(ResultUtil.success());
        }


        @ApiOperation(value = "条件查询", notes = "条件查询")
        @RequestMapping(value = "query", method = RequestMethod.POST)
        public ResponseEntity<Page<Message>> query(@RequestBody PageBody pageBody) {
            Page<Message> page = null;
            try {
                page = imMessageService.query(pageBody);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(page);
        }

}
