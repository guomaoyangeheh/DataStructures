package com.gmy.datastructures.timewheel.timer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author guomaoyang
 * @Date 2020/11/12
 */
public class TimingWheel {
    // 每个刻度的时间跨度(精度)
    private Long tickMs;
    // 多少个刻度
    private Integer wheelSize;
    // 时间轮被创建的起始时间戳
    private Long startMs;
    // 整个时间轮的时间跨度CompletableFuture
    private Long interval;
    // 当前时间
    private Long currentTime;
    // 每个刻度上的任务链表
    private TimerTaskEntryList[] buckets;
    // 当前时间轮上的任务数
    private AtomicInteger taskCounter;
    private DelayQueue<TimerTaskEntryList> delayQueue;
    // 上层时间轮
    private TimingWheel overflowWheel;

    public TimingWheel(Long tickMs,Integer wheelSize,Long startMs,AtomicInteger taskCounter,DelayQueue<TimerTaskEntryList> delayQueue){
        this.tickMs =tickMs;
        this.wheelSize = wheelSize;
        this.interval = tickMs * wheelSize;
        this.startMs = startMs;
        this.taskCounter = taskCounter;
        this.delayQueue = delayQueue;
        // 为了取整
        this.currentTime = startMs - (startMs % tickMs);
        buckets = new TimerTaskEntryList[wheelSize];
        // 初始化buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new TimerTaskEntryList(taskCounter);
        }
    }

    // 创建上层时间轮
    private synchronized void addOverflowWheel(){
        if(overflowWheel == null){
            overflowWheel = new TimingWheel(interval,wheelSize,
                                    currentTime,taskCounter,delayQueue);
        }
    }

    public boolean addTask(TimerTaskEntry timerTaskEntry){
        // 任务被执行的时间戳
        Long expirationMs = timerTaskEntry.expirationMs;

        // 如果任务被取消，直接返回false
        if(timerTaskEntry.canceled()){// 分支1
            return false;
            // 超时时间小于当前时间加一个刻度的时间，返回false，此任务被立即执行任务
        }else if(expirationMs < currentTime + tickMs){// 分支2
            return false;
            // 超时时间在当前时间轮的范围内
        }else if(expirationMs < currentTime + interval){// 分支3
            // 计算当前任务应放在哪个槽
            Long virtualId = expirationMs / tickMs;
            long l2 = virtualId % wheelSize;
            int i = (int) l2;
            TimerTaskEntryList bucket = buckets[i];
            bucket.add(timerTaskEntry);
            // 这里为了判断bucket是否重用，若重用则放入delayQueue
            if(bucket.setExpiration(virtualId * tickMs)){
                delayQueue.offer(bucket);
            }
        }else {// 分支4
            // 说明过期时间超过当前时间轮的范围，交由上层时间轮保存
            if(overflowWheel == null){
                addOverflowWheel();
            }
            overflowWheel.addTask(timerTaskEntry);
        }
        return true;
    }

    // 推动时间轮
    public void advanceClock(long timeMs){
        if(timeMs >= currentTime + tickMs){
            // 这一步主要是为了取个整数，例如tickMs为20ms，传进来时间为123ms,此时就让currentTime=120ms即可；
            currentTime = timeMs - timeMs % tickMs;
            // 推动上层时间轮
            if(overflowWheel != null){
                overflowWheel.advanceClock(timeMs);
            }
        }
    }




}
