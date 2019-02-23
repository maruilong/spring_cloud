package com.xinyuan.setup.rpc;

import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.data.client.InformationClient;
import com.xinyuan.data.model.InformationDTO;
import com.xinyuan.setup.entity.Information;
import com.xinyuan.setup.service.InformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author hzx
 * @date 2019/1/10 17:24
 */

@RestController
@RefreshScope
//@RequestMapping("/information")
public class InformationApi implements InformationClient {

    @Autowired
    private InformationService informationService;

    @Override
    public ResponseEntity<Object> query(@RequestBody PageBody pageBody) {
        Page<InformationDTO> page = null;
        try {
            page = informationService.accountQuery(pageBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody InformationDTO informationDTO) {
        Information information=new Information();
        BeanUtils.copyProperties(informationDTO,information);
        informationService.save(information);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        informationService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @Override
    public ResponseEntity<Object> update(@RequestBody InformationDTO informationDTO) {

        Information information=new Information();
        BeanUtils.copyProperties(informationDTO,information);
        informationService.update(information);
        return ResponseEntity.ok(ResultUtil.success());
    }
}
