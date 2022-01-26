package com.gmy.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author guomaoyang
 * @Date 2020/12/31
 */
public class GraphDemo {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        // A->C
        graph.setWeight(0,2,1);
        // A->B
        graph.setWeight(0,1,1);
        // B->C
        graph.setWeight(1,2,1);
        // B->E
        graph.setWeight(1,4,1);
        // B->D
        graph.setWeight(1,3,1);
        boolean[] isVisit = {false,false,false,false,false};
        boolean[] isVisit2 = {false,false,false,false,false};
        graph.dfs(isVisit,0);
        System.out.println();
        graph.bfs(isVisit2,0);

    }
}
class MyGraph{
    private List<String> list;
    private int[][] arr;
    private int sides = 0;

    public MyGraph(){
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        arr = new int[list.size()][list.size()];
    }

    /**
     * 图的深度优先搜索(Depth First Search)
     */
    public void dfs(boolean[] isVisit,int i){
        if(isVisit[i]){
           return;
        }
        System.out.print(list.get(i)+"->");
        isVisit[i] = true;
        // 获取当前节点的临近节点
        int m = nearNode(i);
        while(m != -1){
            dfs(isVisit,m);
            m = nextNode(i,m);
        }
    }

    /**
     * 图的广度优先搜索(Broad First Search)
     * @param isVisit
     * @param i
     */
    public void bfs(boolean[] isVisit,int i){
        if(!isVisit[i]){
            System.out.print(list.get(i)+"->");
            isVisit[i] = true;
        }
        // 找到当前节点的所有邻接节点
        LinkedList<Integer> linkedList = new LinkedList<>();

        // 获取当前节点的临近节点
        int m = nearNode(i);
        while(m != -1){
            if(isVisit[m]){
                m = nextNode(i,m);
                continue;
            }
            System.out.print(list.get(m)+"->");
            isVisit[m] = true;
            linkedList.addLast(m);
            m = nextNode(i,m);
        }
        if(linkedList.size() > 0){
            Integer first = linkedList.removeFirst();
            while(linkedList.size() > 0){
                bfs(isVisit,first);
                first = linkedList.removeFirst();
            }
        }
    }

    private int nextNode(int i, int m) {
        int[] ints = arr[i];
        for(int j = m+1;j<ints.length;j++){
            if(ints[j] > 0){
                return j;
            }
        }
        return -1;
    }

    private int nearNode(int i) {
        int[] ints = arr[i];
        for (int i1 = 0; i1 < ints.length; i1++) {
            if(ints[i1] > 0){
                return i1;
            }
        }
        return -1;
    }

    public void setWeight(int i1,int i2,int weight){
        arr[i1][i2] = weight;
        arr[i2][i1] = weight;
        sides++;
    }

}
