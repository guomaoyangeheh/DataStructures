package com.gmy.datastructures.timewheel.timer;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @Author guomaoyang
 * @Date 2020/11/11
 */
public class TimerTaskEntryList implements Delayed {
    private TimerTaskEntry root  = new TimerTaskEntry(null,-1);
    private AtomicLong expiration = new AtomicLong(-1L);

    public TimerTaskEntryList(AtomicInteger taskCounter) {
        this.taskCounter = taskCounter;
        root.next = root;
        root.prev = root;
    }

    // 当前list任务数量
    private AtomicInteger taskCounter;

    // 过期时间戳，任务需要被执行的时间戳
    public long getExpiration() {
        return expiration.get();
    }

    /**
     * 目前 Kafka 使用一个 DelayQueue 统一管理所有的 Bucket，也就是 TimerTaskList 对象。
     * 随着时钟不断向前推进，原有 Bucket 会不断地过期，然后失效。当这些 Bucket 失效后，源码会重用这些 Bucket。
     * 重用的方式就是重新设置 Bucket 的过期时间，并把它们加回到 DelayQueue 中。这里进行比较的目的，就是用来判断这个 Bucket 是否要被插入到 DelayQueue。
     */
    public boolean setExpiration(long expirationMs) {
        // 这一步为了判断当前bucket是否被重用，旧值和当前值不一样返回ture表明重用了，再次放入delayQueue；
        return this.expiration.getAndSet(expirationMs) != expirationMs;
    }


    /**
     * 删除指定元素
     */
    public synchronized void remove(TimerTaskEntry timerTaskEntry){
        if(timerTaskEntry.list == this){
            timerTaskEntry.next.prev = timerTaskEntry.prev;
            timerTaskEntry.prev.next = timerTaskEntry.next;
            timerTaskEntry.next = null;
            timerTaskEntry.prev = null;
            timerTaskEntry.list = null;
            taskCounter.decrementAndGet();
        }
    }

    /**
     * 添加元素
     */
    public synchronized void add(TimerTaskEntry timerTaskEntry){
        boolean flag = false;
        while(!flag){
            timerTaskEntry.remove();
            synchronized (timerTaskEntry){
                if(timerTaskEntry.list == null){
                    TimerTaskEntry tail = root.prev;
                    timerTaskEntry.next = root;
                    timerTaskEntry.prev = tail;
                    timerTaskEntry.list = this;
                    root.prev = timerTaskEntry;
                    tail.next = timerTaskEntry;
                    taskCounter.incrementAndGet();
                    flag = true;
                }
            }
        }
    }

    /**
     * 将链表的所有元素重新插入时间轮
     * @param consumer
     */
    public synchronized void flush(Consumer<TimerTaskEntry> consumer){
        TimerTaskEntry head = root.next;
        while (head != root){
            // 首先从链表上移除当前节点
            remove(head);
            // 重新插入时间轮
            consumer.accept(head);
            head = root.next;
        }
        expiration.set(-1L);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(getExpiration() - System.currentTimeMillis(),TimeUnit.MILLISECONDS );
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS),o.getDelay(TimeUnit.MILLISECONDS));
    }
}
