package com.gmy.datastructures.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author guomaoyang
 * @Date 2021/1/5
 */
public class PrimDemo {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int [][]weight=new int[][]{{10000,5,7,10000,10000,10000,2},
            {5,10000,10000,9,10000,10000,3},
            {7,10000,10000,10000,8,10000,10000},
            {10000,9,10000,10000,10000,4,10000},
            {10000,10000,8,10000,10000,5,4},
            {10000,10000,10000,4,5,10000,6},
            {2,3,10000,10000,4,6,10000},};
        Graph graph = new Graph(data, weight);

        graph.prim('A');

    }
}
class Graph{
    int nodeNums;
    int[][] arr;
    char[] chars;
    public Graph(char[] chars,int[][] arr){
        this.chars = chars;
        this.arr = arr;
        this.nodeNums = chars.length;
    }

    public void show(){
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public  void prim(char a){
        Map<Character, Boolean> map = new HashMap<>();
        map.put(a,true);
        int minimum = 10000;
        int n1 = -1;
        int n2 = -1;
        /**
         * 最小生成树只会有 nodesNum-1条边
         */
        for (int i = 0; i < nodeNums - 1; i++) {

            for (int k = 0; k < nodeNums; k++) {
                if(map.get(chars[k]) == null){
                    continue;
                }
                for (int j = 0; j < nodeNums; j++) {
                    if(chars[k] == chars[j]){
                        continue;
                    }
                    if(map.get(chars[j]) == null && arr[k][j] < minimum){
                        minimum = arr[k][j];
                        n1 = k;
                        n2 = j;
                    }
                }
            }
            // 确定一条边
            System.out.println(chars[n1] + "->" +chars[n2] + "权值：" + minimum);
            map.put(chars[n2],true);
            n1 = -1;
            n2 = -1;
            minimum = 10000;
        }
    }
}