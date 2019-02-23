package com.xinyuan.setup.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.setup.entity.Code;
import org.springframework.stereotype.Repository;

/**
 * @Description: 代码
 * @author hzx
 * @date 2019/1/1018:02
 */
@Repository
public interface CodeRepository extends BaseJpaRepository<Code, Long> {
}
