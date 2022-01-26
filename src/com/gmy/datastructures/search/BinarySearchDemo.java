package com.gmy.datastructures.search;

/**
 * @Author guomaoyang
 * @Date 2020/11/27
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7,8,9};
        //int i = binarySearch(arr, 0, arr.length - 1, 10);
        int i = binarySearch2(arr, 9);
        System.out.println(i);

    }
    public static int binarySearch(int[] arr,int left,int right,int target){
        int midIndex = (left+right)/2;
        int midVal = arr[(left+right)/2];
        if(left > right ){
            return -1;
        }
        if(target < midVal){
            return binarySearch(arr, left, midIndex-1, target);
        }else if(target > midVal){
           return binarySearch(arr, midIndex+1, right, target);
        }else {
            return midIndex;
        }
    }

    public static int binarySearch2(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
//        int mid = (left + right)/2;
//        while(mid >= left){
//            if(arr[mid] == target){
//                return mid;
//            }else if(arr[mid] > target){
//                right = mid-1;
//            }else{
//                left = mid +1;
//            }
//            mid = (left + right)/2;
//        }
        while(left <= right){
            int mid = (left + right)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid-1;
            }else{
                left = mid +1;
            }
        }
        return -1;
    }


    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int size = A.length;
        int count =0;
        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                for(int k =0;k<size;k++){
                    for(int z =0;z<size;z++){
                        int result = A[i] + B[j]+C[k]+D[z];
                        if(result == 0){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
