package com.xinyuan.task.service;

import com.xinyuan.base.service.BaseService;
import com.xinyuan.task.common.enums.TaskStatus;
import com.xinyuan.task.common.enums.TaskType;
import com.xinyuan.task.entity.Task;
import com.xinyuan.task.mapper.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liang
 */
@Service
public class TaskService extends BaseService<TaskRepository, Task, Long> {

    public List<Task> queryByTypeAndStatus(TaskType taskType, TaskStatus taskStatus) {
        return bizRepository.queryByTypeAndStatus(taskType.name(), taskStatus.name());
    }

    public void updateStatus(Task task, TaskStatus taskStatus) {
        task.setStatus(taskStatus.name());
        update(task);
    }

    public Task createTask(String content, TaskType taskType) {
        Task task = new Task();
        task.setContent(content);
        task.setStatus(TaskStatus.SUBMITTED.name());
        task.setType(taskType.name());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        task.setDeleted(0);
        return save(task);
    }

}
