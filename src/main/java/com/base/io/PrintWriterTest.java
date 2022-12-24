package com.base.io;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PrintWriterTest {
    public static void main(String[] args) throws Exception {
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.println("print writer");
        printWriter.close();

        PrintWriter printWriter1 = new PrintWriter(new FileOutputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/printwrite.out"));
        printWriter1.println("print writer");
        printWriter1.close();
    }
}
