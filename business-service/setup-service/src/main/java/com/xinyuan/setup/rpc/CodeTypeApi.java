package com.xinyuan.setup.rpc;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.client.CodeTypeClient;
import com.xinyuan.data.model.CodeTypeDTO;
import com.xinyuan.setup.entity.CodeType;
import com.xinyuan.setup.service.CodeTypeService;
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
public class CodeTypeApi implements CodeTypeClient {

    @Autowired
    private CodeTypeService codeTypeService;

    @Override
    public ResponseEntity<Object> query(@RequestBody PageBody pageBody) {
        Page<CodeTypeDTO> page = null;
        try {
            page = codeTypeService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody CodeTypeDTO codeTypeDTO) {
        CodeType codeType= new CodeType();
        BeanUtils.copyProperties(codeTypeDTO,codeType);
        codeTypeService.save(codeType);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        codeTypeService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody CodeTypeDTO codeTypeDTO) {
        CodeType codeType= new CodeType();
        BeanUtils.copyProperties(codeTypeDTO,codeType);
        codeTypeService.update(codeType);

        return ResponseEntity.ok(ResultUtil.success());
    }
}
