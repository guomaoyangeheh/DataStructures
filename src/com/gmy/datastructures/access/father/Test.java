package com.gmy.datastructures.access.father;

import com.gmy.datastructures.access.Son2;

/**
 * @Author guomaoyang
 * @Date 2020/11/16
 */
public class Test {
    public static void main(String[] args) {
        // 同包子类访问
        Son1 son1 = new Son1();
        System.out.println(son1.protectedNum);
        int defaultNum = son1.defaultNum;
        int defaultNumStatic = son1.defaultNumStatic;
        int protectedNumStatic = son1.protectedNumStatic;

        // 测试类在同包，子类在不同包
        Father father = new Father();
        int defaultNum1 = father.defaultNum;
        int protectedNum = father.protectedNum;

    }
}
