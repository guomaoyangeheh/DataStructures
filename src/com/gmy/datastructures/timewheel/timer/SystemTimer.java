package com.gmy.datastructures.timewheel.timer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author guomaoyang
 * @Date 2020/11/12
 */
public class SystemTimer implements Timer {
    private ExecutorService taskExecutor = Executors.newFixedThreadPool(10);

    private TimingWheel timingWheel;
    private DelayQueue<TimerTaskEntryList> delayQueue = new DelayQueue<>();
    private AtomicInteger taskCounter = new AtomicInteger(0);
    private Long startMs;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public SystemTimer(Long tickMs,int wheelSize,Long startMs){
        this.startMs = startMs;
        timingWheel = new TimingWheel(tickMs,wheelSize,startMs,taskCounter,delayQueue);
    }



    @Override
    public void addTask(TimeTask timeTask) {
        // 包装一层
        readLock.lock();
        try {
            addTimerTaskEntry(new TimerTaskEntry(timeTask,timeTask.delayMs + System.currentTimeMillis()));
        }finally {
            readLock.unlock();
        }
    }

    private void addTimerTaskEntry(TimerTaskEntry timerTaskEntry){
        if(!timingWheel.addTask(timerTaskEntry)){
            if(!timerTaskEntry.canceled()){
                // 添加失败，并且没有被取消的任务直接执行
                taskExecutor.submit(timerTaskEntry.timerTask);
            }
        }
    }

    private void reinsert(TimerTaskEntry timerTaskEntry){
        this.addTimerTaskEntry(timerTaskEntry);
    }

    @Override
    public Boolean advanceClock(long timeMs) throws InterruptedException {
        TimerTaskEntryList bucket = delayQueue.poll(timeMs, TimeUnit.MILLISECONDS);
        if(bucket != null){
            writeLock.lock();
            try {
                while(bucket != null){
                    // 以拿到的bucket的过期时间作为当前时间 推动时间轮
                    timingWheel.advanceClock(bucket.getExpiration());
                    // 将bucket的所有任务重新插入时间轮(此方法的精妙之处在于 过期的任务会在重新插入的时候被直接执行，没有过期的任务会被降级)
                    bucket.flush(this::reinsert);
                    bucket = delayQueue.poll();
                }
            }finally {
                writeLock.unlock();
            }
        }
        return false;
    }
}
