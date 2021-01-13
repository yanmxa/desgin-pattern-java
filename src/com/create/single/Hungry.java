package com.create.single;

/**
 * 饿汉模式：
 *      1. 类加载到jvm中后，就实例一个对象，JVM保证线程安全， 推荐使用
 *      2. 缺点是有时候可能会不需要这个对象，但是已经实例化了
 */
public class Hungry {

    private Hungry() {}
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }

    public static void main(String[] args) {
        Hungry hungry1 = getInstance();
        Hungry hungry2 = getInstance();
        System.out.println(hungry1 == hungry2);
    }
}


