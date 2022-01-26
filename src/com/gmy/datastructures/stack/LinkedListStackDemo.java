package com.gmy.datastructures.stack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author guomaoyang
 * @Date 2020/11/16
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        linkedListStack.add(new StackEntry(1,"小王"));
        linkedListStack.add(new StackEntry(2,"小明"));
        linkedListStack.add(new StackEntry(3,"小李"));
        linkedListStack.add(new StackEntry(4,"小葱"));
        linkedListStack.add(new StackEntry(5,"小苹"));
        linkedListStack.add(new StackEntry(6,"小香"));
        linkedListStack.show();
        System.out.println("弹出--------------------------");
        while(!linkedListStack.isEmpty()){
            System.out.println(linkedListStack.pop());
        }

    }
}
class LinkedListStack{
    public int size;
    public AtomicInteger top;
    // 哨兵节点
    public StackEntry root = new StackEntry(-1,null);

    public LinkedListStack (int size){
        this.size = size;
        top = new AtomicInteger(-1);
    }

    public boolean isFull(){
        if(top.get() == size-1){
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        if(top.get() == -1){
            return true;
        }
        return false;
    }

    // 头插法添加，保持头部一直是最新添加的
    public void add(StackEntry stackEntry){
        if(isFull()){
            System.out.println("栈满，不可添加！");
            return;
        }
        stackEntry.next = root.next;
        root.next = stackEntry;
        top.incrementAndGet();
    }

    // 弹栈
    public StackEntry pop (){
        if(isEmpty()){
            throw new RuntimeException("栈空！");
        }
        StackEntry entry = root.next;
        root.next = entry.next;
        entry.next = null;
        top.decrementAndGet();
        return entry;
    }

    public void show(){
        if(isEmpty()){
            System.out.println("栈空！");
        }
        StackEntry next = root.next;
        while (next != null){
            System.out.println(next);
            next = next.next;
        }
    }
}

class StackEntry{
    public int value;
    public String name;
    public StackEntry next;
    public StackEntry(int value,String name){
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StackEntry{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
