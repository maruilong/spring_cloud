package com.xinyuan.setup.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.setup.entity.Information;
import com.xinyuan.setup.mapper.InformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzx
 * @Description: 信息集
 * @date 2019/1/10 17:19
 */
@Service
public class InformationService extends BaseService<InformationRepository, Information, Long> {

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
