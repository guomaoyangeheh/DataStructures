package com.gmy.datastructures.tree;

/**
 * @Author guomaoyang
 * @Date 2020/12/4
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1, "宋江");
        Node node1 = new Node(2, "播求");
        Node node2 = new Node(3, "林冲");
        Node node3 = new Node(4, "卢俊义");
        Node node4 = new Node(5, "羔子");
        tree.setRoot(root);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        System.out.println("前序遍历...");
        tree.pre();

        System.out.println("中序遍历...");
        tree.mid();

        System.out.println("后序遍历...");
        tree.post();

        // 前序查找
        /*System.out.println(root.preFind(4));
        System.out.println(root.midFind(5));
        System.out.println(root.postFind(6));*/

        //删除
        /*System.out.println("已删除"+tree.delNode(3));
        tree.pre();*/

    }
}

class BinaryTree{
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }
    public void pre(){
        if(root != null){
            root.pre2();
        }
    }

    public void mid(){
        if(root != null){
            root.mid();
        }
    }

    public void post(){
        if(root != null){
            root.post();
        }
    }
    public Node delNode(int no){
        if(this.root != null){

            if(this.root.getNo() == no){
                Node tem = this.root;
                this.root = null;
                return tem;
            }else{
                return this.root.delNode(no);
            }
        }else{
            return null;
        }

    }

}

class Node{
    private String name;
    private int no;
    Node left;
    Node right;

    public Node( int no,String name) {
        this.name = name;
        this.no = no;
    }

    public void pre2(){
        System.out.println(this);
        if(this.left != null){
            this.left.pre();
        }
        if(this.right != null){
            this.right.pre();
        }

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
    // 前序遍历
    public void pre(){
        System.out.println(this);
        if(this.left != null){
            this.left.pre();
        }
        if(this.right != null){
            this.right.pre();
        }
    }
    // 中序遍历
    public void mid(){
        if(this.left != null){
            this.left.mid();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.mid();
        }
    }

    // 后序遍历
    public void post(){
        if(this.left != null){
            this.left.post();
        }
        if(this.right != null){
            this.right.post();
        }
        System.out.println(this);
    }
    public Node preFind(int no){
        if(this.no == no){
            return this;
        }
        Node node = null;
        if(this.left != null){
            node = this.left.preFind(no);
        }
        if(node != null){
            return node;
        }
        if(this.right != null){
            node = this.right.preFind(no);
        }
        return node;

    }

    public Node midFind(int no){
        Node node = null;
        if(this.left != null){
            node = this.left.midFind(no);
        }
        if(node != null){
            return node;
        }
        if(this.no == no){
            return this;
        }

        if(this.right != null){
            node = this.right.midFind(no);
        }
        return node;
    }

    public Node postFind(int no){
        Node node = null;
        if(this.left != null){
            node = this.left.postFind(no);
        }
        if(node != null){
            return node;
        }

        if(this.right != null){
            node = this.right.postFind(no);
        }
        if(node != null){
            return node;
        }
        if(this.no == no){
            return this;
        }
        return node;
    }

    public Node delNode(int no){
        Node temp = null;
        if(this.left != null && this.left.no == no){
            temp = this.left;
            this.left = null;
            return temp;
        }
        if(this.left != null){
            temp = this.left.delNode(no);
        }
        if(temp != null){
            return temp;
        }

        if(this.right != null && this.right.no == no){
            temp = this.right;
            this.right = null;
            return temp;
        }
        if(this.right != null){
            temp = this.right.delNode(no);
        }
        return temp;
    }

    public Node preFind2(int no) {
        if(this.no == no){
            return this;
        }
        if(this.left != null){
            Node node = this.left.preFind2(no);
            if(node != null){
                return node;
            }
        }
        if(this.right != null){
            Node node = this.right.preFind2(no);
            if(node != null){
                return node;
            }
        }
        return null;
    }
}