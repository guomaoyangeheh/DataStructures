package com.gmy.datastructures.graph;

import java.util.Arrays;

/**
 * @Author guomaoyang
 * @Date 2021/1/13
 */
public class FloydDemo {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' }; //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        FloydGraph floydGraph = new FloydGraph(vertex, matrix);
        floydGraph.show();
        floydGraph.floyd2();
        System.out.println("进行过floyd算法后............");
        floydGraph.show();

    }
}

class FloydGraph{
    private char[] vertex; // 存放顶点的数组
    private int length;
    private int[][] dis; // 保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
    private char[][] pre;// 保存到达目标顶点的前驱顶点

    public FloydGraph(char[] vertex,int[][] dis){
        this.dis = dis;
        this.vertex = vertex;
        this.length = vertex.length;
        pre = new char[length][length];
        for(int i = 0; i< length;i++){
            Arrays.fill(pre[i],vertex[i]);
        }
    }

    public void floyd(){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    int len = dis[i][j]+ dis[i][k];
                    if(len < dis[k][j]){
                        // 更新2节点间的距离
                        dis[j][k] = len;
                        dis[k][j] = len;
                        // 跟新前驱节点矩阵
                        pre[j][k] = vertex[i];
                        pre[k][j] = vertex[i];
                    }

                }
            }
        }
    }

    public void floyd2() {
        int len;
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发 [A, B, C, D, E, F, G]
            for (int i = 0; i < dis.length; i++) {
                //到达 j 顶点 // [A, B, C, D, E, F, G]
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];// => 求出从 i 顶点出发，经过 k 中间顶点，到达 j 顶点距离
                    if (len < dis[i][j]) {//如果 len 小于 dis[i][j]
                        dis[i][j] = len;//更新距离
                        pre[i][j] = pre[k][j];//更新前驱顶点 }
                    }
                }
            }
        }
    }

    public void show(){
        System.out.println(Arrays.toString(vertex));
        for (int i = 0; i < dis.length; i++) {
            System.out.print(vertex[i]);
            System.out.print(Arrays.toString(dis[i]));
            System.out.println();
        }
        System.out.println("前驱节点矩阵..........");
        System.out.println(Arrays.toString(vertex));
        for (int i = 0; i < dis.length; i++) {
            System.out.print(vertex[i]);
            System.out.print(Arrays.toString(pre[i]));
            System.out.println();
        }
    }

}