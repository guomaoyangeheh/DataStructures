package com.gmy.datastructures.yuesefu;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

/**
 * 环形链表解决约瑟夫问题，重要的就是头尾元素衔接，搞个哨兵节点。
 * @Author guomaoyang
 * @Date 2020/11/13
 */
public class CircularDoubleList {
    Entry root = new Entry("root",-1);
    public CircularDoubleList(){
        // 初始化环形链表
        root.prev = root;
        root.next = root;
    }

    public void add(Entry entry){
        if(entry == null){
            throw new IllegalArgumentException("非法参数");
        }
        Entry tail = root.prev;
        tail.next = entry;
        entry.prev = tail;
        root.prev = entry;
        entry.next = root;
    }

    public void show(){
        Entry temp = root.next;
        while(true){
            if(temp == null || temp == root){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        CircularDoubleList list = new CircularDoubleList();
        list.add(new Entry("小明",1));
        list.add(new Entry("小王",2));
        list.add(new Entry("小华",3));
        list.add(new Entry("小李",4));
        list.add(new Entry("小郭",5));
        list.show();
        System.out.println("============丢手绢，每次喊道3的小朋友出列=================");
        list.out(3);
        System.out.println("============出列后============================");
        list.show();
    }

    public void out(int num){
        if(num <1){
            return;
        }
        Entry temp = this.root;
        while (true){
            for (int i = 0; i < num; i++) {
                temp = temp.next;
                if(temp == root){
                    temp = temp.next;
                }
            }
            if(temp == root){
                break;
            }
            System.out.println(temp);
            this.remove(temp);
        }
    }

    private boolean remove(Entry temp) {
        if(temp == null || temp  == root){
            return false;
        }
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
//        temp.next = null;
//        temp.prev = null;
        return true;
    }

}

class Entry{
    String name;
    int seq;
    Entry prev;
    Entry next;
    public Entry(String name,int seq){
        this.name = name;
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", seq=" + seq +
                '}';
    }
}