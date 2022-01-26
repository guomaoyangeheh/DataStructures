package com.gmy.datastructures.tree.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author guomaoyang
 * @Date 2020/12/16
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 8, 2, 9, 11, 7, 3, 12, 33);
        List<Node> nodes = new ArrayList<>();
        for (Integer integer : list) {
            Node node = new Node(integer);
            nodes.add(node);
        }
        System.out.println(nodes);

        Collections.sort(nodes);
        System.out.println("排序后....");
        System.out.println(nodes);
        System.out.println("创建哈弗曼树...");
        Node node = toHuffManTree(nodes);
        node.preList();
    }
    public static Node toHuffManTree(List<Node> nodes){
        Node root = null;
        while(nodes.size() >1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            root = parentNode;
            // 删除前2个元素
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 加入父元素
            nodes.add(parentNode);
        }
        return root;
    }
}
class Node implements Comparable<Node>{
    int weight;
    Node left;
    Node right;
    public Node(int weight){
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                '}';
    }

    public void preList(){
        System.out.println(this);
        if(this.left != null){
            this.left.preList();
        }
        if(this.right != null){
            this.right.preList();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }
}