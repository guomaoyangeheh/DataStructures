package com.gmy.datastructures.sort;

import java.util.Arrays;

/**
 * @Author guomaoyang
 * @Date 2020/11/25
 */
public class InsertSortDemo {
    public static void main(String[] args) {

        int[] arr = {8,2,5,1,-1,4,-8,1,109,22};
        insertSort(arr);

        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];// 作用就是保留当前值
            int repIndex = i -1;
            while(repIndex >= 0 && num < arr[repIndex]){
                arr[repIndex+1] = arr[repIndex];
                repIndex --;
            }
            if(num != arr[repIndex + 1]){
                arr[repIndex + 1] = num;
            }
        }
    }

    public int singleNumber(int[] nums) {
        insertSort2(nums);
        for(int i =0;i<nums.length;i++){
            if(i>0  ){
                if(i<nums.length-1){
                    if(nums[i] != nums[i+1] && nums[i] != nums[i-1]){
                        return nums[i];
                    }
                }else{
                    if(nums[i] != nums[i-1]){
                        return nums[i];
                    }
                }
            }else{
                if(nums[i] != nums[i+1]){
                   return nums[i];
                }
            }
        }
        return 0;
    }

    public void insertSort2(int[] nums){
        for(int i =1;i<nums.length;i++){
            int num = nums[i];
            int repIndex = i -1;
            while(repIndex >= 0 && nums[repIndex] > num){
                nums[repIndex +1] = nums[repIndex];
                repIndex--;
            }
            if(num != nums[repIndex +1]){
                nums[repIndex +1] = num;
            }
        }

    }

}
