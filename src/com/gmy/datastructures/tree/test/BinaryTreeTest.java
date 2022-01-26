package com.gmy.datastructures.tree.test;

/**
 * @Author guomaoyang
 * @Date 2021/10/8
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.leftNode = node2;
        node1.rightsNode  = node3;
        node2.leftNode = node4;
        node2.rightsNode = node5;
        node3.leftNode = node6;
        node3.rightsNode = node7;

        System.out.println("前序遍历");
        node1.preList();
        System.out.println("中序遍历");
        node1.midList();
        System.out.println("后序遍历");
        node1.postList();



    }
    static class TreeNode{
        public TreeNode(Integer value) {
            this.value = value;
        }
        Integer value;
        TreeNode leftNode;
        TreeNode rightsNode;
        public void preList(){
            System.out.println(this.value);
            if(this.leftNode != null){
                leftNode.preList();
            }
            if(this.rightsNode != null){
                rightsNode.preList();
            }
        }
        public void midList(){

            if(this.leftNode != null){
                leftNode.midList();
            }
            System.out.println(this.value);
            if(this.rightsNode != null){
                rightsNode.midList();
            }
        }
        public void postList(){

            if(this.leftNode != null){
                leftNode.postList();
            }

            if(this.rightsNode != null){
                rightsNode.postList();
            }
            System.out.println(this.value);
        }
    }
}
