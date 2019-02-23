package com.xinyuan.upload.web.controller;

import com.baidu.aip.speech.AipSpeech;
import com.xinyuan.upload.account.AipAccount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: hwz
 * @Date: Created in 15:23 2018/4/12.
 */
@Api(description = "语音转文字")
@RestController
@RequestMapping("/")
public class AipController {

    @ApiOperation(value = "语音转文字", notes = "语音转文字")
    @RequestMapping(value = "aip", method = RequestMethod.POST)
    @ApiImplicitParam(name = "data", value = "语音文件二进制数据Base64", required = true, dataType = "String")
    public ResponseEntity<String> aip(@RequestBody String data) throws Exception{

        AipSpeech client = new AipSpeech(AipAccount.APP_ID, AipAccount.API_KEY, AipAccount.SECRET_KEY);

        byte[] b = Base64.decodeBase64(data.getBytes("UTF-8"));

        JSONObject res = client.asr(b,"wav", 8000, null);

        return ResponseEntity.ok(res.toString(2));
    }

    @ApiOperation(value = "语音转文字（路径）", notes = "语音转文字")
    @RequestMapping(value = "aipSpeech", method = RequestMethod.GET)
    @ApiImplicitParam(name = "path", value = "语音文件路径", required = true, dataType = "String")
    public ResponseEntity<String> aipSpeech(@RequestParam String path) throws Exception{

        AipSpeech client = new AipSpeech(AipAccount.APP_ID, AipAccount.API_KEY, AipAccount.SECRET_KEY);

        JSONObject asrRes = client.asr(path, "wav", 8000, null);

        return ResponseEntity.ok(asrRes.toString(2));
    }
}
