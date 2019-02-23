package com.xinyuan.setup.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.setup.entity.Code;
import com.xinyuan.setup.mapper.CodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzx
 * @Description: 代码
 * @date 2019/1/10 17:19
 */
@Service
public class CodeService extends BaseService<CodeRepository, Code, Long> {

    /**
     * 删除
     *
     * @param ids
     */
    public void removeList(List<Long> ids) {
        for (Long i : ids) {
            remove(i);
        }
    }
}
