package com.xinyuan.log.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.log.entity.SysError;
import com.xinyuan.log.mapper.SysErrorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日常
 * @author hzx
 * @Description:
 * @date 2019/1/10 17:19
 */
@Service
public class SysErrorService extends BaseService<SysErrorRepository, SysError, Long> {

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
