package com.xinyuan.task.schedule;


import com.xinyuan.task.bean.Processor;
import com.xinyuan.task.common.enums.TaskStatus;
import com.xinyuan.task.entity.Task;
import com.xinyuan.task.executor.ExecutorServiceFactoryImpl;
import com.xinyuan.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author liang
 */
public abstract class AbstractTaskSchedule {

    protected ExecutorServiceFactoryImpl executorServiceFactory;
    protected ExecutorService executorService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected ApplicationContext applicationContext;

    protected void process(Processor processor, List<Task> tasks, TaskService taskService, ExecutorService executorService) {
        for (Task task : tasks) {
            taskService.updateStatus(task, TaskStatus.RUNNING);
            executorService.submit(() -> {
                try {
                    if (processor.process(task)) {
                        taskService.updateStatus(task, TaskStatus.SUCCESS);
                    } else {
                        taskService.updateStatus(task, TaskStatus.FAILED);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    taskService.updateStatus(task, TaskStatus.FAILED);
                }
            });
        }
    }
}
