package com.gmy.datastructures.recursion;

/**
 * @Author guomaoyang
 * @Date 2020/11/19
 */
public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println("阶乘："+test(10));

    }
    public static int test(int n){
        if(n == 1){
            return 1;
        }
        return n * test(n-1);
    }
}
