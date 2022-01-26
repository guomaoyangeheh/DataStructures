package com.gmy.datastructures.sort;

import com.sun.media.sound.SF2InstrumentRegion;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 快速排序
 * @Author guomaoyang
 * @Date 2020/11/27
 */
public class FastSortDemo {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-666,-567,123,70, -1,900, 4561};

        //fastSort(arr,0,arr.length-1);
        quickSort2(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

        System.out.println(binarySearch2(arr, 0, arr.length - 1, 4561));


    }
    public static void fastSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int mid = (l+r)/2;
        int midVal = arr[mid];
        // 以mid为中轴，定义左右2个指针，当l指正找到比midVal大的并且r指针找到比midVal小的时候进行交换。
        while(l<r){
            while(arr[l]<midVal){
                l++;
            }
            while(arr[r] > midVal){
                r--;
            }
            if(l >= r){// 如果l指针已经大于等于r指针则中断循环
                break;
            }
            // 交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 判断交换后等于midVal的情况
            if(arr[l] == midVal){
                r--;
            }
            if(arr[r] == midVal){
                l++;
            }
        }
        if(l == r){
            l++;
            r--;
        }
        if(left < r){
            // 递归左半部分
            fastSort(arr,left,r);
        }
        if(right > l){
            // 递归右半部分
            fastSort(arr,l,right);
        }

    }

    public static void quickSort2(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = (left + right)/2;
        int midVal = arr[pivot];
        // 此次循环能保证midVal左边都是小于midVal的，右边是大于的；
        while (l < r){
            while(arr[l] < midVal){
                l++;
            }
            while (arr[r] > midVal){
                r--;
            }
            if(l >= r){
                break;
            }
            // 交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果出现l或r有一个是等于midVal的
            if(arr[l] == midVal){
                r--;
            }
            if(arr[r] == midVal){
                l++;
            }
        }
        if(l == r){// 2个指针如果相等，则分别移动
            l++;
            r--;
        }
        if(left < r){// 左边递归
            quickSort2(arr,left,r);
        }
        if(right > l){// 右边递归
            quickSort2(arr,l,right);
        }
    }
    public static int binarySearch2(int[] arr,int left,int right,int target){
        if(right < left){
            return -1;
        }
        int midIndex = (left + right)/2;
        int midVal = arr[midIndex];
        if(midVal > target) {
            return binarySearch2(arr, left, midIndex-1, target);
        }else if(midVal < target){
            return binarySearch2(arr,midIndex+1,right,target);
        }else {
            return midIndex;
        }

    }
}
