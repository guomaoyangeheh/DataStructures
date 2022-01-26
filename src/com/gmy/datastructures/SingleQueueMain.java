package com.gmy.datastructures;

/**
 * @Author guomaoyang
 * @Date 2020/10/23
 */
public class SingleQueueMain {



    public static void main(String[] args) {
        SingleQueue singleQueue = new SingleQueue();
        singleQueue.sortedAdd(new Node(3,"小明"));
        singleQueue.sortedAdd(new Node(6,"小华"));
        singleQueue.sortedAdd(new Node(1,"小王"));
        singleQueue.sortedAdd(new Node(1,"小王"));
        singleQueue.sortedAdd(new Node(-88,"小王"));
        singleQueue.sortedAdd(new Node(199,"小王"));
        singleQueue.sortedAdd(new Node(0,"小猪"));
        singleQueue.sortedAdd(new Node(28,"小猪"));
        singleQueue.sortedAdd(new Node(-5,"小猪"));
        singleQueue.show();
        System.out.println("翻转-------------------");
        singleQueue.reverse();

    }


}
class SingleQueue{
    public Node head = new Node();
    public int capacity;


    public boolean isEmpty(){
        if(head.next == null){
            return true;
        }
        return false;
    }

    public boolean sortedAdd(Node node){
        if(head.next == null){
            head.next = node;
            return true;
        }
        Node temp = head;
        // 当前已经有序，判断是否比下一个节点小，小的话就插前面，如果一直没有比他小的就插在尾部，说明这个是最大的
        boolean flag = false;
        Node tail = null;
        while(temp.next != null){
            if(node.age <= temp.next.age){// 大于的话就插入
                node.next = temp.next;
                temp.next = node;
                flag = true;
                break;
            }
            tail = temp.next;
            temp = temp.next;
        }
        if(flag){
            return true;
        }else {
            // 查到尾部
            tail.next = node;
            return true;
        }
    }
    public void show(){
        Node temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
    // 翻转本队列
    public void reverse(){
        SingleQueue reverseQueue = new SingleQueue();
        Node temp = this.head;
        while(temp.next != null){
            // 拿出这个货
            Node curr = temp.next;
            temp.next = temp.next.next;
            curr.next = null;

            // 装到新集合
            curr.next = reverseQueue.head.next;
            reverseQueue.head.next = curr;
        }
        reverseQueue.show();
    }
}
class Node{
    public int age;
    public String name;
    public Node next;
    public Node() {

    }

    public Node(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
