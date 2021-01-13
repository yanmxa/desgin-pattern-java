package com.create.single;

/**
 * 懒汉式：
 *      1. 虽然达到了按需实例化的目的，但是却也带来了线程安全的问题
 *      2. syn放在static方法上锁住的这个class对象，非static方法上面是锁在实例上面
 */
public class Lazy {

    private Lazy() {
//        System.out.println(Thread.currentThread().getName() + " create Lazy object.");
    }

    private static Lazy lazy;

    public static Lazy getInstance1() {

        if (lazy == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lazy = new Lazy();
        }
        return lazy;
    }


    public static synchronized Lazy getInstance2() {
        if (lazy == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lazy = new Lazy();
        }
        return lazy;
    }

    /*
 3      * 利用静态变量来记录Singleton的唯一实例
 4      * volatile 关键字确保：当uniqueInstance变量被初始化成Singleton实例时，
 5      * 多个线程正确地处理uniqueInstance变量
 6      *
 7      */
    public static Lazy getInstance3() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }





    // 多线城下造成问题
    public static void main(String[] args) {
        long start;
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Lazy.getInstance1());
            }).start();
        }
        System.out.println("no sync " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Lazy.getInstance2());
            }).start();
        }
        System.out.println("sync method " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Lazy.getInstance3());
            }).start();
        }
        System.out.println("sync region " + (System.currentTimeMillis() - start));
    }

}
