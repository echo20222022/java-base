package com.base.interview;

public class Singleton {

    //饿汉式
    /*private final static Singleton SINGLETON = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return SINGLETON;
    }*/

    //懒汉式
    private static volatile Singleton singleton;
    private Singleton() {}
    private static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //枚举
    public enum Singletion1 {
        INSTANCE;
        public void method() {
            System.out.println("println");
        }
    }

}
