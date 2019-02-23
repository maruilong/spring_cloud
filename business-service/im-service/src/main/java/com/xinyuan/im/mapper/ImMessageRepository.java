package com.xinyuan.im.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.im.entity.ImMessage;
import org.springframework.stereotype.Repository;

/**
 * @author liang
 */
@Repository
public interface ImMessageRepository extends BaseJpaRepository<ImMessage, Long> {
}
