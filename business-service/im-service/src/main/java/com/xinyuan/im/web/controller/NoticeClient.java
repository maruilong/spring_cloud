package com.xinyuan.im.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xinyuan.im.web.request.RequestMessage;
import com.xinyuan.im.web.response.ResponseMessage;
import com.xinyuan.im.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

/**
 * 执行通知操作的客户端
 *
 * @author liang
 */
@Slf4j
@Controller
public class NoticeClient {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SimpUserRegistry userRegistry;

    /**
     * @param requestMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     */
    @MessageMapping("/notice")
    @SendTo("/topic/getNoticeResponse")
    public ResponseMessage notice(RequestMessage requestMessage) {
        SimpUser user = userRegistry.getUser(requestMessage.getName());
        log.info("通知消息:" + JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSender(requestMessage.getSender());
        responseMessage.setContent(requestMessage.getContent());

            return responseMessage;
            //存储消息的 Redis 列表名
//            String listKey = requestMessage.getName();
//
//            //存储消息到Redis中
//            redisService.addToListRight(listKey, responseMessage);
    }


}
