package com.xinyuan.setup.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.setup.entity.CodeType;
import com.xinyuan.setup.mapper.CodeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzx
 * @Description: 代码类型
 * @date 2019/1/10 17:19
 */
@Service
public class CodeTypeService extends BaseService<CodeTypeRepository, CodeType, Long> {

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
