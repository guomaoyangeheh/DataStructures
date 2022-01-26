package com.gmy.datastructures.algorithm;

/**
 * 分治算法：汉诺塔案例
 * @Author guomaoyang
 * @Date 2020/12/24
 */
public class TowerDemo {
    public static void main(String[] args) {
        start(3,'A','B','C');

    }
    public static void start(int num,char a,char b,char c){
        if(num == 1){
            // 最后一个盘从a->c
            System.out.println("将第1个盘从 " + a + " -> " + c);
        }else{
            // 1.上面的盘都看作第一个盘 a -> b
            start(num-1,a,c,b);
            // 2.下面的盘看做最后一个盘，从a -> c
            System.out.println("将第"+num + "个盘从 " + a +" -> "+c);
            // 3.将剩下所有的盘从 b -> c
            start(num-1,b,a,c);
        }

    }
}
