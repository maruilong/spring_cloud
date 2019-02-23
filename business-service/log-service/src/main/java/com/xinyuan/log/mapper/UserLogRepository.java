package com.xinyuan.log.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.log.entity.UserLog;
import org.springframework.stereotype.Repository;

/**
 * 用户日志
 * @author hzx
 * @Description:
 * @date 2019/1/1017:08
 */
@Repository
public interface UserLogRepository extends BaseJpaRepository<UserLog, Long> {

}
