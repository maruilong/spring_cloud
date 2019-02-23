package com.xinyuan.account.service.hystric;

import com.xinyuan.account.service.CodeService;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.model.CodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 断路器 测试
 */
@Slf4j
@Component
public class CodeHystric implements CodeService {

    @Override
    public ResponseEntity<Object> query(PageBody pageBody) {
        System.out.println("hello");
        List<String> list=new ArrayList<>();
        list.add("1");
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Object> save(CodeDTO codeDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(List<Long> ids) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(CodeDTO codeDTO) {
        return null;
    }
}
