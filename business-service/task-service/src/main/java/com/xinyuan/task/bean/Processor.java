package com.xinyuan.task.bean;

import com.xinyuan.task.entity.Task;
import com.xinyuan.task.executor.ExecutorServiceFactoryImpl;
import com.xinyuan.task.service.TaskService;

import java.util.concurrent.ExecutorService;

/**
 * @author liang
 */
public abstract class Processor {

    protected ExecutorServiceFactoryImpl executorServiceFactory;

    protected ExecutorService executorService;

    protected TaskService taskService;


    public Processor(ExecutorServiceFactoryImpl executorServiceFactory,
                     ExecutorService executorService) {
        this.executorServiceFactory = executorServiceFactory;
        this.executorService = executorService;
    }

    /**
     * 任务的执行方法
     *
     * @param task
     * @return
     */
    public abstract boolean process(Task task);

}
