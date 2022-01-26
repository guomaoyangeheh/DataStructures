package com.gmy.datastructures.delayqueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author guomaoyang
 * @Date 2020/11/9
 */
public class MyDelayedTask extends Thread implements Delayed {
    private String name;
    private long startTime = System.currentTimeMillis();
    private long time ;
    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MyDelayedTask(String name, long time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println("当前时间："+ LocalDateTime.now().format(df) + "任务" + name + "开始执行！");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime + time- System.currentTimeMillis(),unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MyDelayedTask> delayedTasks = new DelayQueue<>();

        MyDelayedTask task1 = new MyDelayedTask("task1", 5000L);
        MyDelayedTask task2 = new MyDelayedTask("task2", 10000L);
        System.out.println("启动时间：" + LocalDateTime.now().format(df));
        delayedTasks.offer(task1);
        delayedTasks.offer(task2);
        while(true){
            MyDelayedTask take = delayedTasks.take();
            take.start();
        }


    }
}
