package com.gmy.datastructures.tree;

/**
 * 顺序存储二叉树
 * @Author guomaoyang
 * @Date 2020/12/7
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5,6,7,8};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(ints);
        System.out.println("前序遍历....");
        arrBinaryTree.preList();
        System.out.println("中序遍历....");
        arrBinaryTree.midList();
        System.out.println("后序遍历....");
        arrBinaryTree.postList();
    }

}
class ArrBinaryTree{
    int[] arr;
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }
    public void preList(){
        this.preList(0);
    }
    public void midList(){
        this.midList(0);
    }
    public void postList(){
        this.postList(0);
    }
    public void preList(int index){
        System.out.println(arr[index]);
        if((2*index+1) < arr.length){
            preList(2*index+1);
        }
        if((2*index+2) < arr.length){
            preList(2*index+2);
        }
    }

    public void midList(int index){

        if((2*index+1) < arr.length){
            midList(2*index+1);
        }
        System.out.println(arr[index]);
        if((2*index+2) < arr.length){
            midList(2*index+2);
        }
    }

    public void postList(int index){

        if((2*index+1) < arr.length){
            postList(2*index+1);
        }
        if((2*index+2) < arr.length){
            postList(2*index+2);
        }
        System.out.println(arr[index]);
    }

}