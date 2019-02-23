package com.xinyuan.setup.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.setup.entity.CodeType;
import org.springframework.stereotype.Repository;

/**
 * @Description: 代码类型
 * @author hzx
 * @date 2019/1/1018:04
 */
@Repository
public interface CodeTypeRepository  extends BaseJpaRepository<CodeType, Long> {
}
