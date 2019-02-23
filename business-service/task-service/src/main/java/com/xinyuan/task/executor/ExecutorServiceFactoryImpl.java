// Copyright 2016 Baidu Inc. All rights reserved.

package com.xinyuan.task.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The executor service factory.
 *
 * @author Jian Hengyi (jianhengyi@baidu.com)
 */
@Component
public class ExecutorServiceFactoryImpl implements ExecutorServiceFactory {

    private final BlockingQueueFactory blockingQueueFactory;

    @Autowired
    public ExecutorServiceFactoryImpl(BlockingQueueFactory blockingQueueFactory) {
        this.blockingQueueFactory = blockingQueueFactory;
    }

    @Override
    public ExecutorService newFixedThreadPool(int size) {
        return new ThreadPoolExecutor(size, size, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }

    @Override
    public ExecutorService newFixedThreadPool(String name, int size) {
        return new ThreadPoolExecutor(size, size, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new NamedThreadFactory(name));
    }

    @Override
    public ExecutorService newFixedThreadPool(String name, int size,
            BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(size, size, 0, TimeUnit.SECONDS,
                workQueue, new NamedThreadFactory(name));
    }

    @Override
    public ExecutorService newFixedBoundThreadPool(int threadSize, int queueSize) {
        BlockingQueue<Runnable> queue = blockingQueueFactory.createBlockingQueue(queueSize);
        return new ThreadPoolExecutor(threadSize, threadSize, 0, TimeUnit.SECONDS, queue);
    }

    @Override
    public ExecutorService newSingleThreadExecutor(final String name,
            BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, workQueue,
                new NamedThreadFactory(name));
    }

    @Override
    public ExecutorService newCacheThreadExecutor(final String name) {
        return Executors.newCachedThreadPool(new NamedThreadFactory(name));
    }

    @Override
    public ScheduledExecutorService newSingleThreadScheduledExecutor(String name) {
        return newFixedThreadScheduledExecutor(name, 1);
    }

    @Override
    public ScheduledExecutorService newFixedThreadScheduledExecutor(String name, int threadCount) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(threadCount,
                new NamedThreadFactory(name));

        // Because Spring use the delay interface of ScheduledThreadPoolExecutor, we need to set
        // executeExistingDelayedTasksAfterShutdown to false to make sure it can be shutdown.
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);

        return executor;
    }

    private static final class NamedThreadFactory implements ThreadFactory {

        private final String name;
        private int threadIndex = 0;

        private NamedThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable target) {
            return new Thread(target, name + "-" + (threadIndex++));
        }

    }

}
