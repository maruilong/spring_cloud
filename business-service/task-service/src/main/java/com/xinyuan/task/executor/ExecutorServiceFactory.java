// Copyright 2016 Baidu Inc. All rights reserved.

package com.xinyuan.task.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Factory for creating {@link ExecutorService}.
 *
 * @author Jian Hengyi (jianhengyi@baidu.com)
 */
public interface ExecutorServiceFactory {

    /**
     * Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
     * queue.
     *
     * @param size The number of threads in the pool.
     * @return The newly created thread pool.
     */
    ExecutorService newFixedThreadPool(int size);

    /**
     * Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
     * queue.
     *
     * @param name The thread pool value.
     * @param size The number of threads in the pool.
     * @return The newly created thread pool.
     */
    ExecutorService newFixedThreadPool(String name, int size);

    /**
     * Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
     * queue.
     *
     * @param name      The thread pool value.
     * @param size      The number of threads in the pool.
     * @param workQueue The queue to use for holding tasks before they are executed.
     * @return The newly created thread pool.
     */
    ExecutorService newFixedThreadPool(String name, int size, BlockingQueue<Runnable> workQueue);

    /**
     * Creates a thread pool that reuses a fixed number of threads operating off a shared bounded
     * queue. Submitting tasks to the thread pool will be blocked if the queue is full, until some
     * tasks are taken from the queue.
     *
     * @param threadSize The number of threads in the pool.
     * @param queueSize  The size of the queue.
     * @return The newly created thread pool.
     */
    ExecutorService newFixedBoundThreadPool(int threadSize, int queueSize);

    /**
     * Creates an executor that uses a single worker thread operating off the given blocking queue.
     *
     * @param name      The thread value.
     * @param workQueue The queue to use for holding tasks before they are executed.
     * @return The newly created thread pool.
     */
    ExecutorService newSingleThreadExecutor(String name, BlockingQueue<Runnable> workQueue);

    /**
     * creates an executor that uses a cache thread pool.
     *
     * @param name The thread value.
     * @return The newly created thread pool.
     */
    ExecutorService newCacheThreadExecutor(String name);

    /**
     * Creates a single-threaded executor that can schedule commands to run after a given delay, or
     * to execute periodically.
     *
     * @param name The thread value.
     * @return The newly created scheduled executor.
     */
    ScheduledExecutorService newSingleThreadScheduledExecutor(String name);

    /**
     * Creates a fixed-threaded executor that can schedule commands to run after a given delay, or
     * to execute periodically.
     *
     * @param name        The thread value.
     * @param threadCount The thread count.
     * @return The newly created scheduled executor.
     */
    ScheduledExecutorService newFixedThreadScheduledExecutor(String name, int threadCount);

}
