package com.base.interview;

public class MutiThreadPrint {

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> { System.out.println("A"); });
        Thread b = new Thread(() -> { System .out.println("B"); });
        Thread c = new Thread(() -> { System.out.println("C"); });
        a.start();
        a.join();

        b.start();
        b.join();

        c.start();
        c.join();
    }
}
