package com.gmy.datastructures.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * @Author guomaoyang
 * @Date 2020/11/24
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {2,-1,5,3,1};
        bubbleSort(arr);
        // 排序耗时测算
        /*int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        System.out.println("排序耗时"+(System.currentTimeMillis() - start)+"ms");
*/
    }
    public static void bubbleSort(int[] arr){
        // 首先明白一点，每一波排序都会确定一个最大值，也就是说最多会经历length - 1波排序
        for (int i = 0; i < arr.length-1; i++) {
            int temp = 0;
            boolean flag = true;
            // 精髓就在内部这个for循环的arr.length-1-i，例如第一波，必须要两两比较所有的数，经过第一波确定了一个最大值
            // 第二波我只需要比较前边4个数然后再从4个数中确定一个最大值，第三波两两比较前3个数....
            for(int j = 0; j < arr.length-1-i; j++){
                // 如果前面的比后边的大就交换
                if(arr[j] > arr[j+1]){
                    // 如果经历了这一波都没有交换说明排序已经完毕
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.println("经历过第"+(i+1)+"波排序");
//            System.out.println(Arrays.toString(arr));
            if(flag){
                break;
            }
        }
    }
}
