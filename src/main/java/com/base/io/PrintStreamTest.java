package com.base.io;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {

    public static void main(String[] args) throws Exception{
        PrintStream printStream = System.out;
        printStream.println("print stream");

        //修改标准输出的位置
        System.setOut(new PrintStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/sys.out"));
        System.out.println("print stream");
        printStream.close();


    }
}
