package com.gmy.datastructures.sort;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author guomaoyang
 * @Date 2020/12/9
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {8,2,12,1,19,5,1,3,-12,-2,6,90,9};
        heapSort4(arr);
        System.out.println("排序后："+ Arrays.toString(arr));

    }

    public static void heapSort4(int[] arr){
        // 遍历所有的非叶子节点
        for(int j = arr.length/2-1; j >=0; j--){
            topBig(arr,j,arr.length);
        }
        // 收尾置换，直到排序完成
        /*for (int i = 0; i < arr.length; i++) {
            int bigger = arr[0];
            arr[0] = arr[arr.length-1 -i];
            arr[arr.length-1 -i] = bigger;
            topBig(arr,0,arr.length-1-i);
        }*/

        for(int i = arr.length -1; i >= 0; i--){
            int bigger = arr[0];
            arr[0] = arr[i];
            arr[i] = bigger;
            topBig(arr,0,i);
        }

    }
    // 将小标为index的数搞成大顶堆
    private static void topBig(int[] arr, int index, int length) {
        int temp = arr[index];// 当前节点的值
        for(int k = 2*index +1; k<length; k = 2 * k + 1){// 先找到左子节点
            // 先拿左右子子节点比较
            if(k + 1 < length && arr[k+1] > arr[k]){
                k++;
            }
            if(temp < arr[k]){
                arr[index] = arr[k];// 与子节点较大值交换
                index = k;// 交换完后有可能打破了子节点树的平衡，所以将index 设为子节点的下标，然后再进行子节点树的置换。
            }else{
                break;
            }
        }
        arr[index] = temp;
    }

    public static void heapSort3(int[] arr){
        // 最后一个非叶子节点
        int latestNonLeafNodeIndex = arr.length / 2 - 1;
        for(int i = latestNonLeafNodeIndex; i >= 0;i--){
            adjustHeap3(arr,i,arr.length);
        }
        for(int i = arr.length-1;i > 0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap3(arr,0,i);
        }

    }
    public static void adjustHeap3(int[] arr,int index,int length){
        // 搞个临时值存储当前节点
        int temp = arr[index];
        /**
         * 循环是因为交换后会破坏下一层小树的结构，所以要循环往下层处理
         */
        for (int i = index * 2 +1; i < length ; i = i*2+1) {
            // 从左右子节点获取个较大
            if(i+1 < length && arr[i] < arr[i+1]){
                i++;// 到这说明右节点比子节点大
            }
            if(arr[i] > temp){// 比此值大就要将index节点换下去
                // 将大值放到原index位置
                arr[index] = arr[i];
                // 将index设为i
                index = i;
            }else {
                break;
            }
        }
        arr[index] = temp;
    }
    public static void heapSort(int[] arr){
        // 经过这一波循环数组已经是一个大顶堆了。
        for(int i = arr.length/2-1;i >= 0;i --){
            adjustHeap(arr,i,arr.length);
        }

        for(int k = arr.length-1;k>0;k--){
            int temp = arr[k];
            arr[k] = arr[0];
            arr[0] = temp;// 首尾交换，保证尾部是最大的
            adjustHeap(arr,0,k);// 不断从大顶堆的最上层取值，替换root后adjustHeap方法可以保证数依然是大顶堆，看下面循环的注释即可明白
        }

    }

    public static void adjustHeap(int[] arr,int index,int length){
        int temp = arr[index];
        // 找当前节点的左子节点通过公式 2*index+1 即可，不行画图看
        /**
         * 首先确定以index为父节点这个小树的最大值，如果本身index最大则不做任何处理
         * 如果是左右子节点比index大，则用大的节点替换index节点，循环的意义在于替换后会让原先的node节点为父节点的小树不平衡，
         * 所以还需要将这颗小树的三个节点进行比较选出最大的节点依次反复，直到被替换的节点都是大顶堆
         * 总结：循环的意义是防止替换节点后影响下层树不平衡，所以要保证节点被替换后依然是大顶堆
         */
        for(int i = 2*index+1 ; i < length; i=2*i+1){
            if(i+1 < length && arr[i] < arr[i+1]){
                i++;// 左子节点如果没有右子节点大，则将i指向右子节点（右节点在数组中的体现只需要左子节点坐标+1）
            }
            if(arr[i] > temp){
                arr[index] = arr[i];
                index = i;
            }else{
                break;
            }
        }
        arr[index] = temp;
    }

    public static void heapSort2(int[] arr){
        // 从最后一个非叶子节点开始（从右至左，从下至上），经过此次循环保证arr为大顶堆
        for(int j = arr.length/2-1;j>=0;j--){
            adjustHeap2(arr,j,arr.length);
        }

        for(int i = arr.length-1;i>0;i--){
            int temp = arr[i];
            arr[i] = arr[0];// index0 一定是最大值
            arr[0] = temp;
            adjustHeap2(arr,0,i);// 替换完root后，大顶堆已经不再平衡，所以通过这个方法再整理一下大顶堆
        }

    }

    public static void adjustHeap2(int[] arr,int i,int length){
        int temp = arr[i];
        // for循环从当前节点的左子节点开始
        for(int k = 2*i+1;k<length;k = 2*k+1){
            if(k+1<length && arr[k]>arr[k+1]){
                k++;// 右节点比左大，则将k移到右节点
            }
            if(arr[k] < temp){
                arr[i] = arr[k];// 将大的节点替换到父节点
                i = k;// 将i的坐标移到这个节点，这个时候先不着急给arr[k] 赋值，要看一k为父节点的小树需不需要替换
            }else{
                break;// 不需要替换直接终止循环，说明以i为祖先节点的树都是平衡的都是大顶堆
            }
        }
        arr[i] = temp;
    }
    class Solution {
        public int minArray(int[] numbers) {
            if(numbers.length == 0){
                return -1;
            }
            if(numbers.length == 1){
                return numbers[0];
            }
            int l = numbers[numbers.length-1];// 左段的最大值
            int r = numbers[0];// 右段的最小值
            return 1;

        }
        /*private int getIdx(int[] numbers,int s,int e,int l,int r){
            if(s == e){
                return numbers[s];
            }
            int halfIdx = (s+e)/2;
            if(numbers[halfIdx] < numbers[halfIdx -1]){
                return numbers[halfIdx];
            }




        }*/
    }
}
