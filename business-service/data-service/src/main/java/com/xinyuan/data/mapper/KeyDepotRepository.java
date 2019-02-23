package com.xinyuan.data.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.data.entity.KeyDepot;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyDepotRepository extends BaseJpaRepository<KeyDepot, Long> {
}
