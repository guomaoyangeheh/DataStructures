package com.gmy.datastructures.timewheel.timer;

import com.gmy.datastructures.timewheel.timer.TimeTask;

/**
 * @Author guomaoyang
 * @Date 2020/11/12
 */
public interface Timer {

    void addTask(TimeTask timeTask);

    Boolean advanceClock(long timeMs) throws InterruptedException;


}
