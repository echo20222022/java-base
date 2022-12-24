package com.base.io;

import java.util.Scanner;

public class StandardInOutTest {

    public static void main(String[] args) {
        //java.io.BufferedInputStream
        System.out.println(System.in.getClass());
        //java.io.PrintStream
        System.out.println(System.out.getClass());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("input -> " + input);
    }
}
