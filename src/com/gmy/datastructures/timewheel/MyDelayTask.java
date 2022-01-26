package com.gmy.datastructures.timewheel;

import com.gmy.datastructures.timewheel.timer.TimeTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author guomaoyang
 * @Date 2020/11/12
 */
public class MyDelayTask extends TimeTask {
    String name;
    // 期望被执行时间
    long exceptTime;

    public MyDelayTask(long delayMs,String name){
        super(delayMs);
        this.exceptTime = System.currentTimeMillis() + delayMs;
        this.name = name;
    }
    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        System.out.println("taskName:"+name+",计划时间："+format.format(new Date(exceptTime))+",执行时间:"+format.format(new Date(System.currentTimeMillis())));
    }
}
