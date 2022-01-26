package com.gmy.datastructures.innerclass;

import com.gmy.datastructures.access.father.Son1;

/**
 * @Author guomaoyang
 * @Date 2021/2/2
 */
public class MemberClass {
    private static String name = "zhangsan";
    private Son1 son1;
    public MemberClass(){
        System.out.println("MemberClass.construct");
    }
    private void method(){
        son1.method1();
        System.out.println(Son4.name);

    }

    /**
     * 只能被外部类的实例调用(包范围内)
     * MemberClass memberClass = new MemberClass();
     * MemberClass.Son1 son1 = memberClass.new Son1();
     */
    class Son1{
        private void method1(){
            System.out.println(name);
            method();
        }
    }
    public class Son2{
    }
    /**
     * 可以被直接调用(包范围内)
     * new MemberClass.Son2();
     */
    static class Son3{
    }
    public static class Son4{
        private static String name = "wangwu";
    }
}
