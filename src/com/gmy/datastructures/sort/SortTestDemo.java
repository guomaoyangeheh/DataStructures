package com.gmy.datastructures.sort;

import java.util.*;

/**
 * @Author guomaoyang
 * @Date 2020/11/25
 */
public class SortTestDemo {
    public static void main(String[] args) {
        int[] ints = new int[8000000];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(8000000);
        }

        int[] arr4 = ints.clone();
        long s4 = System.currentTimeMillis();
        ShellSortDemo.shellSort1(arr4);
        // 希尔排序
        System.out.println("希尔排序耗时：" + (System.currentTimeMillis() - s4) + "ms");

        int[] arr5 = ints.clone();
        long s5 = System.currentTimeMillis();
        FastSortDemo.fastSort(arr5,0,arr5.length-1);
        // 快速排序
        System.out.println("快速排序耗时：" + (System.currentTimeMillis() - s5) + "ms");

        int[] arr6 = ints.clone();
        long s6 = System.currentTimeMillis();
        HeapSortDemo.heapSort(arr6);
        // 堆排序
        System.out.println("堆排序耗时：" + (System.currentTimeMillis() - s6) + "ms");

        int[] arr7 = ints.clone();
        long s7 = System.currentTimeMillis();
        Arrays.sort(arr7);
        // JDK排序
        System.out.println("JDK排序耗时：" + (System.currentTimeMillis() - s7) + "ms");

        /*int[] arr3 = ints.clone();
        long s3 = System.currentTimeMillis();
        InsertSortDemo.insertSort(arr3);
        // 插入排序
        System.out.println("插入排序耗时：" + (System.currentTimeMillis() - s3) + "ms");


        int[] arr2 = ints.clone();
        long s2 = System.currentTimeMillis();
        SelectSort.selectSort(arr2);
        // 选择排序
        System.out.println("选择排序耗时：" + (System.currentTimeMillis() - s2) + "ms");*/


//        int[] arr = ints.clone();
//        long s1 = System.currentTimeMillis();
//        BubbleSortDemo.bubbleSort(arr);
//        // 冒泡排序
//        System.out.println("冒泡排序耗时：" + (System.currentTimeMillis() - s1) + "ms");




    }

}
