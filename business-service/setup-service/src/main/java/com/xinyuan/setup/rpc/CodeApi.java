package com.xinyuan.setup.rpc;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.client.CodeClient;
import com.xinyuan.data.model.CodeDTO;
import com.xinyuan.setup.entity.Code;
import com.xinyuan.setup.service.CodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 代码
 * @author hzx
 * @date 2019/1/10 17:24
 */

@RestController
public class CodeApi implements CodeClient {
    @Autowired
    private CodeService codeService;

    @Override
    public ResponseEntity<Object> query(@RequestBody PageBody pageBody) {
        Page<CodeDTO> page = null;
        try {
            page = codeService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody CodeDTO codeDTO) {
        Code code= new Code();
        BeanUtils.copyProperties(codeDTO,code);
        codeService.save(code);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        codeService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody CodeDTO codeDTO) {
        Code code= new Code();
        BeanUtils.copyProperties(codeDTO,code);
        codeService.update(code);
        return ResponseEntity.ok(ResultUtil.success());
    }
}
