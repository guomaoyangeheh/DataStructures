package com.gmy.datastructures.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author guomaoyang
 * @Date 2020/11/26
 */
public class ShellSortDemo {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0,10};
//        int[] ints = new int[1000];
//        Random random = new Random();
//        for (int i = 0; i < ints.length; i++) {
//            ints[i] = random.nextInt(1000);
//        }
//        shellSort1(ints);

        // 分解步骤
        int temp = 0 ;

        for(int i = 5;i<arr.length;i++){
            int repIndex = -1;
            temp = arr[i];
            for(int j = i-5;j >= 0;j-=5){
                if(temp<arr[j]){
                    arr[j+5] = arr[j];
                    repIndex = j;
                }
            }
            if(repIndex != -1){
                arr[repIndex] = temp;
            }
        }
        System.out.println("第1波排序后：" + Arrays.toString(arr));

        for(int i = 2;i<arr.length;i++){
            int repIndex = -1;
            temp = arr[i];
            for(int j = i-2;j >= 0;j-=2){
                if(temp<arr[j]){
                    arr[j+2] = arr[j];
                    repIndex = j;
                }
            }
            if(repIndex != -1){
                arr[repIndex] = temp;
            }
        }
        System.out.println("第2波排序后：" + Arrays.toString(arr));


        for(int i = 1;i<arr.length;i++){
            int repIndex = -1;
            temp = arr[i];
            for(int j = i-1;j >= 0;j-=1){
                if(temp<arr[j]){
                    arr[j+1] = arr[j];
                    repIndex = j;
                }
            }
            if(repIndex != -1){
                arr[repIndex] = temp;
            }
        }
        System.out.println("第3波排序后：" + Arrays.toString(arr));
    }

    /**
     * 希尔排序可以理解为插入排序的升级版
     * 插入排序的缺点就是如果小元素在后边会造成大量的移动降低排序效率，
     * 而希尔排序采用分组的方式将相同跨度的每组元素排好序，尽可能降低小数靠后造成的移动消耗
     */
    public static void shellSort1(int[] arr){
        int c = arr.length;
        int temp;

        while((c/=2)>0){
            for(int i = c;i<arr.length;i++){
                int repIndex = -1;
                temp = arr[i];
                // 不要被这个跨度c迷惑，用跨度来保证处理的是同组的元素，其实组内就是使用的插入排序
                for(int j = i-c;j >= 0;j-=c){
                    if(temp<arr[j]){
                        arr[j+c] = arr[j];
                        repIndex = j;
                    }else{
                        // 每一组中当前元素前边的元素已经是有序的了，如果arr[n]>arr[n-1],也就没必要和前面的元素比较了，直接中断提升效率
                        break;
                    }
                }
                if(repIndex != -1){
                    arr[repIndex] = temp;
                }
            }
            //System.out.println("第"+(++count)+"波排序后：" + Arrays.toString(arr));
        }
    }
}
