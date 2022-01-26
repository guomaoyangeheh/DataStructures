package com.gmy.datastructures.timewheel.timer;

/**
 * @Author guomaoyang
 * @Date 2020/11/11
 */
public abstract class TimeTask implements Runnable {
    // 定时任务延迟时间
    Long delayMs;
    private TimerTaskEntry timerTaskEntry;
    public TimeTask(long delayMs){
        this.delayMs = delayMs;
    }

    public TimerTaskEntry getTimerTaskEntry(){
        return this.timerTaskEntry;
    }
    public void setTimerTaskEntry(TimerTaskEntry timerTaskEntry){
        synchronized (this){
            if (timerTaskEntry != null && timerTaskEntry != this.timerTaskEntry) {
                timerTaskEntry.remove();
                this.timerTaskEntry = timerTaskEntry;
            }
        }
    }
    public void cancel(){
        synchronized (this){
            if(timerTaskEntry != null){
                timerTaskEntry.remove();
            }
            timerTaskEntry = null;
        }
    }
}
