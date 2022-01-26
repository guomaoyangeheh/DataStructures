package com.gmy.datastructures.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @Author guomaoyang
 * @Date 2020/11/24
 */
public class SelectSort {
    /**
     * {3,-1,8,5,2}
     * 第1波排序：从arr[1]-qrr[n-1] 找到一个最小的数，和arr[0] 交换
     * 第2波排序：从arr[2]-qrr[n-1] 找到一个最小的数，和arr[1] 交换
     */
    public static void main(String[] args) {
        int[] arr = {-89, -1, 8, 5, 2,-89,-1,109,79};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));


    }
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int leastIndex= 0 ;
            for (int j = i+1; j < arr.length; j++) {
                if(leastIndex == 0 || arr[leastIndex] > arr[j]){
                    leastIndex = j;
                }
            }
            if(arr[i] > arr[leastIndex]){
                int temp = arr[i];
                arr[i] = arr[leastIndex];
                arr[leastIndex] = temp;
            }
        }
    }
    class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1Index = l1;
        ListNode l2Index = l2;
        ListNode newHead = null;
        ListNode newCurrent = null;
        while(l1Index != null || l2Index !=null){
            if(newHead == null){
                if(l1Index.val >= l2Index.val){
                    newHead = l2Index;
                    newCurrent = l2Index;
                    l2Index = l2Index.next;
                }else{
                    newHead = l1Index;
                    newCurrent = l1Index;
                    l1Index = l1Index.next;
                }
            }else{
                if(l1Index == null && l2Index != null){
                    newCurrent.next = l2Index;
                    newCurrent = l2Index;
                    l2Index = l2Index.next;
                    newCurrent.next = null;

                }else if(l1Index != null && l2Index == null){
                    newCurrent.next = l1Index;
                    newCurrent = l1Index;
                    l1Index = l1Index.next;
                    newCurrent.next = null;

                }else if(l1Index != null && l2Index != null){
                    if(l1Index.val >= l2Index.val){
                        newCurrent.next = l2Index;
                        newCurrent = l2Index;
                        l2Index = l2Index.next;
                        newCurrent.next = null;
                    }else{
                        newCurrent.next = l1Index;
                        newCurrent = l1Index;
                        l1Index = l1Index.next;
                        newCurrent.next = null;
                    }
                }
            }
        }
        return newHead;

    }
}
