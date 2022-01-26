package com.gmy.datastructures.access.father;

/**
 * @Author guomaoyang
 * @Date 2020/11/16
 */
public class Son1 extends Father {
    public void accessFather(){
        //System.out.println(privateNum);
        System.out.println(defaultNum);
        System.out.println(protectedNum);
        System.out.println(defaultNumStatic);
        System.out.println(protectedNumStatic);
    }
}
