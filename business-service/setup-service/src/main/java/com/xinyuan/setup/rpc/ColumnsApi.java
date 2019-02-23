package com.xinyuan.setup.rpc;
/**
 * 列
 */

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.client.ColumnClient;
import com.xinyuan.data.model.ColumnDTO;
import com.xinyuan.setup.entity.Columns;
import com.xinyuan.setup.service.ColumnsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColumnsApi implements ColumnClient {

    @Autowired
    private ColumnsService columnsService;

    @Override
    public ResponseEntity<Object> query(@RequestBody PageBody pageBody) {
        Page<ColumnDTO> page = null;
        try {
            page = columnsService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);

    }

    @Override
    public ResponseEntity<Object> save(@RequestBody ColumnDTO codeDTO) {

        Columns columns=new Columns();
        BeanUtils.copyProperties(codeDTO,columns);
        columnsService.save(columns);

        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        columnsService.removeList(ids);

        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody ColumnDTO codeDTO) {
        Columns columns=new Columns();
        BeanUtils.copyProperties(codeDTO,columns);
        columnsService.update(columns);

        return ResponseEntity.ok(ResultUtil.success());
    }
}
