package com.gmy.datastructures.tree.threaded;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 线索二叉树
 * @Author guomaoyang
 * @Date 2020/12/7
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1, "宋江");
        Node node1 = new Node(3, "播求");
        Node node2 = new Node(6, "林冲");
        Node node3 = new Node(8, "卢俊义");
        Node node4 = new Node(10, "羔子");
        Node node5 = new Node(14, "剑皇");
        tree.setRoot(root);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;

        /*tree.threaded();
        System.out.println(node4);
        System.out.println(node4.left);
        System.out.println(node4.right);*/
        tree.layerList();


    }
}

class BinaryTree{
    private Node root;
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }
    public void threaded(){
        this.threaded(root);
    }
    public void layerList(){
        layerList(root);
    }

    public void layerList(Node node){
        ArrayList<Node> list = new ArrayList<>(); // 存放每一层的数据
        list.add(node);
        System.out.println(node);
        while(list.size() >0){
            ArrayList<Node> second = new ArrayList<>();
            for (Node node1 : list) {
                if(node1.left != null){
                    System.out.println(node1.left);
                }
                if(node1.right != null){
                    System.out.println(node1.right);
                }
                if(node1.left != null){
                    second.add(node1.left);
                }
                if(node1.right != null){
                    second.add(node1.right);
                }
            }
            list = second;
        }

    }
    public void threaded(Node node){
        if(node.left != null){
            threaded(node.left);
        }

        // 设置前驱节点
        if(node.left == null){
            node.left = pre;
            node.leftType = 1;
        }
        // 设置后置节点
        if(pre != null && pre.right  == null){
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;

        if(node.right != null){
            threaded(node.right);
        }
    }

}

class Node{
    private String name;
    private int no;
    Node left;
    Node right;
    int leftType;// 0：左节点，1：前驱节点
    int rightType;// 0：右节点，1：后继节点

    public Node( int no,String name) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

}
