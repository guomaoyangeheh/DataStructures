package com.gmy.datastructures.tree2;

/**
 * @Author guomaoyang
 * @Date 2020/12/21
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12,  1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }

        System.out.println("添加完成遍历树");
        binarySortTree.midList();

//        System.out.println("查找Node");
//        System.out.println("查找Node:" +binarySortTree.findNode(3));
//        System.out.println("查找父Node:" +binarySortTree.findParent(7));
        // 删除
        binarySortTree.delNode(3);
        System.out.println("删除节点后遍历");
        binarySortTree.midList();
    }
}
class BinarySortTree{
    Node root;
    public BinarySortTree(){
        this.root = root;
    }
    public void add(Node node){
        if(this.root == null){
            this.root = node;
            return;
        }
        root.add(node);
    }
    public boolean delNode(int val){
        Node node = findNode(val);
        if(node == null){
            return false;
        }
        Node parent = findParent(val);
        // 说明此节点为root节点
        if(parent == null){
            // 只有这一个节点
            if(node.left == null && node.right == null){
                this.root = null;
            }else if(node.left == null){// 有右子节点
                this.root = node.right;
            }else if(node.right == null){// 有左子节点
                this.root = node.left;
            }else{// 左右节点都不为空
                Node right = node.right;
                Node tempParent = node;
                Node temp = right;
                while(true){
                    if(temp.left != null){
                        tempParent = temp;
                        temp  = temp.left;
                    }else {
                        break;
                    }
                }
                if(root != tempParent){
                    // 正常情况
                    tempParent.left = temp.right;
                    // 成为root
                    root.val = temp.val;
                }else{
                    temp.left = root.left;
                    root = temp;
                }
            }
            return true;
        }else{
            // 如果删除的是叶子节点
            if(node.left == null && node.right == null){
                if(parent.left == node){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
                return true;
            }else{
                // 非叶子节点的情况
                // 先找个能替代自己的节点
                if(node.right != null){
                    Node tempParent = node;
                    Node temp = node.right;
                    // 从右边子树找个最小的节点
                    while(true){
                        if(temp.left != null){
                            tempParent = temp;
                            temp  = temp.left;
                        }else {
                            break;
                        }
                    }
                    if(node != tempParent){
                        // 正常情况
                        tempParent.left = temp.right;
                        // 替代被删节点
                        node.val = temp.val;
                    }else{
                        node.right = temp.right;
                        node.val = temp.val;
                    }
                    return true;
                }else{
                    // 左子树不为空
                    // 找左子树最大的
                    Node tempParent = node;
                    Node temp = node.left;
                    // 从右边子树找个最小的节点
                    while(true){
                        if(temp.right != null){
                            tempParent = temp;
                            temp  = temp.right;
                        }else {
                            break;
                        }
                    }
                    if(node != tempParent){
                        tempParent.right = temp.left;
                        node.val = temp.val;
                    }else{
                        node.left = temp.left;
                        node.val = temp.val;
                    }
                    return true;
                }
            }
        }

    }

    public void midList(){
        if(this.root == null){
            System.out.println("root节点为空");
            return;
        }
        this.root.midList();
    }
    public Node findNode(int val){
        if(this.root == null){
            return null;
        }
       return this.root.findNode(val);
    }

    public Node findParent(int val){
        if(this.root == null || this.root.val == val){
            return null;
        }
        return root.findParent(val);
    }
}
class Node{
    int val;
    Node left;
    Node right;
    public Node(int val){
        this.val = val;
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
    public Node findNode(int val){
        if(val == this.val){
            return this;
        }
        if(val > this.val){
            if(this.right == null){
                return null;
            }else{
               return this.right.findNode(val);
            }
        }else {
            if(this.left == null){
                return null;
            }else{
                return this.left.findNode(val);
            }
        }
    }
    public Node findParent(int val){
        if(this.val == val){// 只有root节点能走到这个分支
            return null;
        }
        if(this.left != null && this.left.val == val){
            return this;
        }
        if(this.right != null && this.right.val == val){
            return this;
        }
        if(val > this.val){
            if(this.right == null){
                return null;
            }else{
                return this.right.findParent(val);
            }
        }else {
            if(this.left == null){
                return null;
            }else{
                return this.left.findParent(val);
            }
        }
    }
}