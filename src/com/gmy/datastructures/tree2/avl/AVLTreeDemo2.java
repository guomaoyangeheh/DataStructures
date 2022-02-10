package com.gmy.datastructures.tree2.avl;

import java.util.Arrays;

/**
 * @Author guomaoyang
 * @Date 2022/2/9
 */
public class AVLTreeDemo2 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        AVLTree2 avlTree = new AVLTree2();
        for (int i : arr) {
            avlTree.addNode(i);
        }
        System.out.println("前序遍历");
        avlTree.pre();
        System.out.println("中序遍历");
        avlTree.mid();
        System.out.println("后序遍历");
        avlTree.post();
        System.out.println("树的高度："+avlTree.getHeight());
        System.out.println("根节点左子树的高度："+avlTree.getLeftHeight());
        System.out.println("根节点右子树的高度："+avlTree.getRightHeight());

    }
}
class AVLTree2{
    private Node2 root;
    public int getLeftHeight(){
        if (root == null){
            return 0;
        }
        return root.getLeftHeight();
    }
    public int getRightHeight(){
        if (root == null){
            return 0;
        }
        return root.getRightHeight();
    }
    public int getHeight(){
        if (root == null){
            return 0;
        }
        return root.getHeight(root);
    }
    public void addNode(int val){
        if(root  == null){
            Node2 node2 = new Node2();
            node2.val = val;
            root = node2;
        }else {
            Node2 node2 = new Node2();
            node2.val = val;
            Node2 tempNode = root;
            while(true){
                if(tempNode.val < node2.val){
                    if(tempNode.right == null){
                        tempNode.right = node2;
                        break;
                    }
                    tempNode = tempNode.right;
                }else if (tempNode.val > node2.val){
                    if(tempNode.left == null){
                        tempNode.left = node2;
                        break;
                    }
                    tempNode = tempNode.left;
                }else {
                    // 相同不添加节点
                    break;
                }
            }
            // 右子树高度 - 左子树高度 > 1 进行左旋转平衡
            if(getRightHeight() - getLeftHeight() > 1){
                // 左旋的时候看下右子树的左子树高度 - 右子树的右子树高度 > 1,如果是，则需要将右子树进行旋转
                if(root.right.getLeftHeight() - root.right.getRightHeight() >= 1){
                    root.right = rightRotate(root.right);
                }

                root = leftRotate(root);
            }else if(getLeftHeight() - getRightHeight() > 1){
                // 右旋的时候看下左子树的右子树高度 - 左子树的左子树高度 > 1,如果是，则需要将左子树进行旋转
                if(root.left.getRightHeight() - root.left.getLeftHeight() >= 1){
                    root.left = leftRotate(root.left);
                }
                // 进行右旋转
                root = rightRotate(root);
            }
        }
    }
    // 左旋转
    private Node2 leftRotate(Node2 targetNode){
        // 创建一个新节点
        Node2 node2 = new Node2(targetNode.val);
        // 新节点的左节点指向当前节点的左子节点
        node2.left = targetNode.left;
        // 新节点的右子节点执行当前节点右子节点的左子节点
        node2.right = targetNode.right.left;
        // 再创建一个新节点（将root的右子节点当做新的root节点）
        Node2 newRoot = new Node2(targetNode.right.val);
        // 新root节点的左子节点指向刚创建的新节点
        newRoot.left = node2;
        // 还指向他原先的右子节点
        newRoot.right = targetNode.right.right;
        return newRoot;
    }

    // 右旋转
    private Node2 rightRotate(Node2 targetNode){
        Node2 node2 = new Node2(targetNode.val);
        node2.right = targetNode.right;
        node2.left = targetNode.left.right;
        Node2 newRoot = new Node2(targetNode.left.val);
        newRoot.left = targetNode.left.left;
        newRoot.right = node2;
        return newRoot;
    }

    public void pre(){
        if (root == null){
            throw new NullPointerException("根节点为空");
        }
        root.pre(root);
    }
    public void mid(){
        if (root == null){
            throw new NullPointerException("根节点为空");
        }
        root.mid(root);
    }
    public void post(){
        if (root == null){
            throw new NullPointerException("根节点为空");
        }
        root.post(root);
    }
}
class Node2{
    int val;
    Node2 left;
    Node2 right;
    public Node2(){

    }
    public Node2(int val){
        this.val =val;
    }

    /**
     * 获取左子树高度
     */
    public int getLeftHeight(){
        if(left == null){
            return 0;
        }
        return Math.max(left.left == null? 0 : getHeight(left.left),left.right == null ? 0 : getHeight(left.right)) + 1;
    }

    /**
     * 获取右子树高度
     */
    public int getRightHeight(){
        if(right == null){
            return 0;
        }
        return Math.max(right.left == null? 0 : getHeight(right.left),right.right == null ? 0 : getHeight(right.right)) + 1;
    }
    public int getHeight(Node2 node){
        return Math.max(node.left == null? 0 :getHeight(node.left),node.right == null? 0:getHeight(node.right))+1;
    }


    // 前中后序遍历
    public void pre(Node2 node){
        System.out.println(node.val);
        if (node.left != null){
            pre(node.left);
        }
        if(node.right != null){
            pre(node.right);
        }
    }
    public void mid(Node2 node){
        if (node.left != null){
            mid(node.left);
        }
        System.out.println(node.val);
        if(node.right != null){
            mid(node.right);
        }
    }
    public void post(Node2 node){
        if (node.left != null){
            post(node.left);
        }
        if(node.right != null){
            post(node.right);
        }
        System.out.println(node.val);
    }
}
