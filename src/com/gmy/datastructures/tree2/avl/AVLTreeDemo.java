package com.gmy.datastructures.tree2.avl;

/**
 * @Author guomaoyang
 * @Date 2020/12/28
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9, 2, 15, 0, 22,3};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        System.out.println("树的高度：" + avlTree.root.height());
        System.out.println("树的左子树高度：" + avlTree.root.leftHeight());
        System.out.println("树的右子树高度：" + avlTree.root.rightHeight());

        avlTree.midList();

    }
}

class AVLTree{
    Node root;
    public void add(Node node){
        if(this.root == null){
            this.root = node;
        }else{
            this.root.add(node);
        }
    }
    public void midList(){
        if(this.root == null){
            System.out.println("树为空");
        }else{
            root.midList();
        }
    }
}

class Node{
    int val;
    Node left;
    Node right;
    public Node(int val){
        this.val = val;
    }
    // 左子树的高度
    public int leftHeight(){
        if(this.left == null){
            return 0;
        }
        return this.left.height();
    }
    // 右子树的高度
    public int rightHeight(){
        if(this.right == null){
            return 0;
        }
        return this.right.height();
    }
    // 树的高度
    public int height(){
        int leftHeight = this.left == null ? 0 : this.left.height();
        int rightHeight = this.right == null ? 0 : this.right.height();
        return Math.max(leftHeight,rightHeight) + 1;
    }
    // 左旋转
    public void leftRotate(){
        // 1.创建个新节点
        Node newNode = new Node(this.val);
        // 2.将当前节点的左子树赋值给新节点的左子树
        newNode.left = this.left;
        // 3.将当前节点的右子树的左子树赋值给新节点的右子树
        newNode.right = this.right.left;
        // 4.将右子树的值赋给当前节点
        this.val = this.right.val;
        // 5.当前节点的右子树指向右子树的右子树
        this.right = this.right.right;
        // 6.当前节点的左子树指向新节点
        this.left = newNode;
        // 打完收工
    }
    // 右旋转
    public void rightRotate(){
        Node newNode = new Node(this.val);
        newNode.right = right;
        newNode.left = left.right;
        val = left.val;
        left = left.left;
        right = newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    public void add(Node node){
        if(node.val >= this.val){
            if(this.right != null){
                this.right.add(node);
            }else{
                this.right = node;
            }
        }else {
            if(this.left != null){
                this.left.add(node);
            }else{
                this.left = node;
            }
        }
        // 右子树高度-左子树高度 > 1则进行左旋转
        if(rightHeight() - leftHeight() > 1){
            // 当前节点的右子树的左子树高度大于右子树时，先将当前节点的右子树进行有旋转，然后再将当前节点进行左旋转
            if(right !=null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();

            // 左子树高度-右子树高度 > 1则进行右旋转
        }else if(leftHeight() - rightHeight() >1){
            // 当前节点左子树的右子树高度大于左子树时，先将当前节点的左子树进行坐左旋转，然后再将当前节点进行右旋转
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
        }
    }
    public void midList(){
        if(this.left != null){
            this.left.midList();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.midList();
        }
    }
}