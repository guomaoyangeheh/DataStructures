package com.gmy.datastructures.access;

import com.gmy.datastructures.access.father.Father;

/**
 * @Author guomaoyang
 * @Date 2020/11/16
 */
public class Son2 extends Father {
    public void accessFather(){
        //System.out.println(defaultNum);
        System.out.println(protectedNum);
        //System.out.println(defaultNumStatic);
        System.out.println(protectedNumStatic);

        int protectedNum = super.protectedNum;
        int protectedNumStatic = super.protectedNumStatic;

        // default 不能被外包访问，外部包的子类也不行
        // protected 不能被外部包直接访问，但是外部包的子类可以访问。

        // 子类中创建父类实例，不可以访问父类的protected方法，通过实例访问其实子类和其他类并无区别
        Father father = new Father();
        // father.protectedNum;

        /**
         * 总结：
         * default：被称为包访问权限不是没有道理，因为只能被同一个包下的子类或者非子类进行访问，即使是不包下的子类也无法访问
         * protected：继承访问权限，包含了包的访问权限，并且支持非同包下的子类进行访问，相对于defaultJ就扩展了这些
         */
    }
}
