package com.xinyuan.auth.service;

import com.xinyuan.auth.entity.UserDataAuth;
import com.xinyuan.auth.mapper.UserDataAuthRepository;
import com.xinyuan.base.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang
 */
@Service
public class UserDataAuthService extends BaseService<UserDataAuthRepository, UserDataAuth, Long> {


    public void removeList(List<Long> ids) {

        for (Long id : ids) {
            remove(id);
        }

    }
}
