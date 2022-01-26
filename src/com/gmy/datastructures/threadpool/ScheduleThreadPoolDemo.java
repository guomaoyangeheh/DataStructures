package com.gmy.datastructures.threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author guomaoyang
 * @Date 2020/12/3
 */
public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        System.out.println("提交任务时间："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        scheduledExecutorService.schedule(()->{
            System.out.println("执行任务时间："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        },6, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(()->{
            System.out.println("执行任务时间："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        },7, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(()->{
            System.out.println("执行任务时间："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        },8, TimeUnit.SECONDS);
    }
}
