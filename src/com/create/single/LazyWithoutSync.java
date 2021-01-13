package com.create.single;

/**
 * 静态内部类方式：
 *      既能达到按需加载，又能避免线程不安全问题
 *      外部内加载到jvm中只有使用内部类时内部类才会被加载
 */
public class LazyWithoutSync {

    private LazyWithoutSync() {}

    private static class Holder {
        private final static LazyWithoutSync instance = new LazyWithoutSync();
    }

    public static LazyWithoutSync getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazyWithoutSync.getInstance());
            }).start();
        }
    }
}
