package com.gmy.datastructures.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author guomaoyang
 * @Date 2020/11/20
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()->{
            try {
                Thread.sleep(1030);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("爸爸到饭店了");
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1010);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("妈妈到饭店了");
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1020);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小明到饭店了");
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println("开饭！");
    }
}
