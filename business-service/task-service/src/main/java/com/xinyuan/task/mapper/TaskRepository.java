package com.xinyuan.task.mapper;

import com.xinyuan.base.mapper.BaseJpaRepository;
import com.xinyuan.task.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liang
 */
@Repository
public interface TaskRepository extends BaseJpaRepository<Task, Long> {
    /***
     *
     * 根绝类型和状态查询任务
     * @param taskType 类型
     * @param taskStatus 状态
     * @return
     */
    List<Task> queryByTypeAndStatus(String taskType, String taskStatus);
}
