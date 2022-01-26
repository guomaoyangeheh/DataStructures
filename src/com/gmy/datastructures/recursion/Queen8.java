package com.gmy.datastructures.recursion;

/**
 * 8皇后问题
 */
public class Queen8 {
    private int max;
    private int[] arr;
    static int count;

    public Queen8(int max){
        this.max = max;
        this.arr = new int[max];
    }
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8(8);
        queen8.go(0);
        System.out.println("一共有"+count+"种解法");
    }

    public void go(int index){
        if(index == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[index] = i;
            if(!conflict(index)){// 不冲突的话放下一行的棋子
                go(index + 1);
            }
        }
    }

    /**
     * 在同一行，同一列，或者同一斜线上意味着冲突
     * 并且只需要判断下边行的
     */
    public boolean conflict(int n){
        for (int i = 0; i < n; i++) {
            // 前者判断是否同一列，后者判断是否在同一斜线上
            if(arr[i] == arr[n] || Math.abs(i - n) == Math.abs(arr[i]-arr[n])){
                return true;
            }
        }
        return false;
    }

    public void print(){
        count++;
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }
}
