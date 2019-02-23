package com.xinyuan.log.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.log.entity.UserLog;
import com.xinyuan.log.mapper.UserLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户日志
 * @author hzx
 * @Description:
 * @date 2019/1/10 17:09
 */
@Service
public class UserLogService extends BaseService<UserLogRepository, UserLog, Long> {

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
