package com.xinyuan.setup.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.setup.entity.Columns;
import org.springframework.stereotype.Repository;

/**
 * @Description:栏目
 * @author hzx
 * @date 2019/1/2817:36
 */
@Repository
public interface ColumnsRepository extends BaseJpaRepository<Columns,Long> {
}
