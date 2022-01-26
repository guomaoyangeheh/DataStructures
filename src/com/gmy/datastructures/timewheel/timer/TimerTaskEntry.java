package com.gmy.datastructures.timewheel.timer;

/**
 * @Author guomaoyang
 * @Date 2020/11/11
 */
public class TimerTaskEntry {
    volatile TimerTaskEntryList list;
    TimerTaskEntry prev;
    TimerTaskEntry next;
    TimeTask timerTask;
    // 过期时间
    long expirationMs;

    public TimerTaskEntry(TimeTask timerTask,long expirationMs){
        if(timerTask != null){
            this.timerTask =timerTask;
            timerTask.setTimerTaskEntry(this);
        }
        this.expirationMs = expirationMs;
    }

    // 任务是否取消
    public Boolean canceled(){
        return timerTask.getTimerTaskEntry() != this;
    }

    /**
     * 置空这个动作是在 TimerTaskList 的 remove 中完成的，而这个方法可能会被其他线程同时调用，
     * 代码使用了 while 循环的方式来确保 TimerTaskEntry 的 list 字段确实被置空了。这样，Kafka 才能安全地认为此链表元素被成功移除。
     */
    public void remove(){
        TimerTaskEntryList currentList = this.list;
        while (currentList != null){
            currentList.remove(this);
            currentList = list;
        }
    }
}
