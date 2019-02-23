package com.xinyuan.setup.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.setup.entity.Information;
import org.springframework.stereotype.Repository;

/**
 * @author hzx
 * @Description: 信息集
 * @date 2019/1/1017:18
 */
@Repository
public interface InformationRepository extends BaseJpaRepository<Information, Long> {
}
