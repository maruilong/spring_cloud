package com.xinyuan.setup.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.setup.entity.Columns;
import com.xinyuan.setup.mapper.ColumnsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author hzx
 * @date 2019/1/28 17:37
 */
@Service
public class ColumnsService extends BaseService<ColumnsRepository, Columns,Long> {

    /**
     * 删除
     *
     * @param ids
     */
    public void removeList(List<Long> ids) {
        for (Long i : ids) {
            remove(i);
        }
    }
}
