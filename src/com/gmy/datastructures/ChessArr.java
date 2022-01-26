package com.gmy.datastructures;

/**
 * 稀疏数组
 * @Author guomaoyang
 * @Date 2020/10/20
 */

public class ChessArr {
    public static void main(String[] args) {
        // 模拟一个棋盘，11行，11列
        int arr[][] = new int[11][11];
        arr[1][2]=1;
        arr[2][3]=2;
        arr[4][5]=2;
        int sum = 0;// 不为0数值计数
        System.out.println("原始数组...");
        for (int[] arrs : arr){
            for (int data : arrs) {
                System.out.printf("%d\t", data);
                if(data != 0){
                    sum++;
                }
            }
            System.out.println();
        }
        // 转为稀疏数组
        int[][] sparseArr = new int[sum + 1][3];// 固定3列，行数为固定首行+值的数量
        // 固定首列
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历原始数组
        int count = 0;// 计数器
        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            for (int i1 = 0; i1 < ints.length; i1++) {
                int data = ints[i1];
                if(data != 0){// 不为0，则需要插入稀疏数组
                    count++;
                    sparseArr[count][0]=i;// 值在原始二位数组的row
                    sparseArr[count][1]=i1;// 值在原始二位数组的col
                    sparseArr[count][2]=data;// 值
                }
            }
        }
        System.out.println("稀疏数组...");
        for (int[] arrs : sparseArr){
            for (int data : arrs) {
                System.out.printf("%d\t", data);
                if(data != 0){
                    sum++;
                }
            }
            System.out.println();
        }
        // 2.稀疏数组转二维数组
        int[][] newArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 从第二行开始遍历稀疏数组
        for (int i = 1; i < sparseArr.length; i++) {
            newArr[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("复盘...");
        for (int[] arrs : newArr){
            for (int data : arrs) {
                System.out.printf("%d\t", data);
                if(data != 0){
                    sum++;
                }
            }
            System.out.println();
        }
    }
}