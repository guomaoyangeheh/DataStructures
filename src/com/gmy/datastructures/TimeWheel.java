package com.gmy.datastructures;

/**
 * @Author guomaoyang
 * @Date 2020/11/7
 */
public class TimeWheel {
    public static void main(String[] args) {
        System.out.println(2011%1000);
        System.out.println(System.currentTimeMillis() - (System.currentTimeMillis()%1000));
    }
}
