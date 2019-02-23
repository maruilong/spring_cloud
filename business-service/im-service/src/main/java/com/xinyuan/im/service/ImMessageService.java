package com.xinyuan.im.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.im.entity.ImMessage;
import com.xinyuan.im.mapper.ImMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liang
 */
@Service
public class ImMessageService extends BaseService<ImMessageRepository, ImMessage, Long> {

    @Transactional
    public void removeList(List<Long> ids) {
        for (Long id : ids) {
            remove(id);
        }
    }
}
