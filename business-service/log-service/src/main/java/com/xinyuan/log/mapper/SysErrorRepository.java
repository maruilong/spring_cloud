package com.xinyuan.log.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.log.entity.SysError;
import org.springframework.stereotype.Repository;

/**
 * 系统日常
 * @author hzx
 * @Description:
 * @date 2019/1/1017:18
 */
@Repository
public interface SysErrorRepository extends BaseJpaRepository<SysError, Long> {
}
