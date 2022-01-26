package com.gmy.datastructures.timewheel;

import com.gmy.datastructures.timewheel.timer.SystemTimer;
import com.gmy.datastructures.timewheel.timer.TimerTaskEntry;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author guomaoyang
 * @Date 2020/11/11
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        SystemTimer systemTimer = new SystemTimer(1L, 20, System.currentTimeMillis());
        for (int i = 1; i <= 10; i++) {
            MyDelayTask myDelayTask = new MyDelayTask(i * 2000, "seq-" + i);
            systemTimer.addTask(myDelayTask);
        }
        // 另起一个线程推动时间轮
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    systemTimer.advanceClock(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    systemTimer.advanceClock(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread.start();
        thread.join();
    }
}
