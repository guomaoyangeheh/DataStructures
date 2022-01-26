package com.gmy.datastructures.arr;

import java.util.Arrays;

/**
 * @Author guomaoyang
 * @Date 2020/11/24
 */
public class ArrDemo {
    public static void main(String[] args) {
        int[][] A = new int[2][3];
        A[0] = new int[]{1,2,3};
        A[1] = new int[]{4,5,6};

        int[][] ints1 = new int[A[0].length][A.length];
        for (int i = 0; i < A[0].length; i++) {
            int[] ints2 = new int[A.length];
            for (int i1 = 0; i1 < A.length; i1++) {
                ints2[i1] = A[i1][i];
            }
            ints1[i] = ints2;
        }

        // 输出结果
        for (int i = 0; i < ints1.length; i++) {
            for (int i1 = 0; i1 < ints1[i].length; i1++) {
                System.out.print(ints1[i][i1]);
            }
            System.out.println();
        }

    }
}
