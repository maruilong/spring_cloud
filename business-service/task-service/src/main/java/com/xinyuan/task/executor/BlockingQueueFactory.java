// Copyright 2016 Baidu Inc. All rights reserved.

package com.xinyuan.task.executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * The blocking queue factory.
 *
 * @author Jian Hengyi (jianhengyi@baidu.com)
 */
@Component
public class BlockingQueueFactory {

    public <E> BlockingQueue<E> createBlockingQueue(int size) {
        BlockingQueue<E> underlyingQueue = null;
        if (size < 0) {
            underlyingQueue = new LinkedBlockingQueue<E>();
        } else if (size == 0) {
            underlyingQueue = new SynchronousQueue<E>(true);
        } else { // size > 0
            underlyingQueue = new ArrayBlockingQueue<E>(size);
        }
        return new ExecutorBlockingQueue<E>(underlyingQueue);
    }

    private static class ExecutorBlockingQueue<E> implements BlockingQueue<E> {

        private static final Log log = LogFactory.getLog(ExecutorBlockingQueue.class);

        private final BlockingQueue<E> underlyingQueue;

        private ExecutorBlockingQueue(BlockingQueue<E> underlyingQueue) {
            this.underlyingQueue = underlyingQueue;
        }

        @Override
        public boolean offer(E item) {
            try {
                underlyingQueue.put(item);
                return true;
            } catch (InterruptedException e) {
                // This is a normal situation, use info log.
                log.info("Offering item to queue is interrupted.", e);
                Thread.currentThread().interrupt();
                return false;
            }
        }

        @Override
        public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
            return underlyingQueue.offer(e, timeout, unit);
        }

        @Override
        public E take() throws InterruptedException {
            return underlyingQueue.take();
        }

        @Override
        public int size() {
            return underlyingQueue.size();
        }

        @Override
        public boolean isEmpty() {
            return underlyingQueue.isEmpty();
        }

        @Override
        public boolean add(E e) {
            return underlyingQueue.add(e);
        }

        @Override
        public Iterator<E> iterator() {
            return underlyingQueue.iterator();
        }

        @Override
        public E remove() {
            return underlyingQueue.remove();
        }

        @Override
        public boolean remove(Object o) {
            return underlyingQueue.remove(o);
        }

        @Override
        public Object[] toArray() {
            return underlyingQueue.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return underlyingQueue.toArray(a);
        }

        @Override
        public E poll() {
            return underlyingQueue.poll();
        }

        @Override
        public E poll(long timeout, TimeUnit unit) throws InterruptedException {
            return underlyingQueue.poll(timeout, unit);
        }

        @Override
        public E element() {
            return underlyingQueue.element();
        }

        @Override
        public E peek() {
            return underlyingQueue.peek();
        }

        @Override
        public void put(E e) throws InterruptedException {
            underlyingQueue.put(e);
        }

        @Override
        public int remainingCapacity() {
            return underlyingQueue.remainingCapacity();
        }

        @Override
        public boolean contains(Object o) {
            return underlyingQueue.contains(o);
        }

        @Override
        public int drainTo(Collection<? super E> c) {
            return underlyingQueue.drainTo(c);
        }

        @Override
        public int drainTo(Collection<? super E> c, int maxElements) {
            return underlyingQueue.drainTo(c, maxElements);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return underlyingQueue.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return underlyingQueue.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return underlyingQueue.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return underlyingQueue.retainAll(c);
        }

        @Override
        public void clear() {
            underlyingQueue.clear();
        }

        @Override
        public boolean equals(Object o) {
            return underlyingQueue.equals(o);
        }

        @Override
        public int hashCode() {
            return underlyingQueue.hashCode();
        }
    }

}
