package com.gmy.datastructures;

/**
 * @Author guomaoyang
 * @Date 2020/11/12
 */
public class extend {
    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

class GrandFather{
    public GrandFather(){
        System.out.println("初始化业爷爷");
    }
    void show(){
        System.out.println(this);

    }
}
class Father extends GrandFather{
    public Father(){
        System.out.println("初始化业爸爸");
    }
    void show(){
        System.out.println(this);
        super.show();
    }
}
class Son extends Father{
    public Son(){
        System.out.println("初始化儿子");
    }
    void show(){
        System.out.println(this);
        super.show();
    }
}