package com.xinyuan.auth.mapper;

import com.xinyuan.auth.entity.UserDataAuth;
import com.xinyuan.base.mapper.BaseJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liang
 */
@Repository
public interface UserDataAuthRepository extends BaseJpaRepository<UserDataAuth, Long> {
}
