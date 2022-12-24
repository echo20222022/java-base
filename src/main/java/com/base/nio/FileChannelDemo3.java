package com.base.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelDemo3 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/aaa.txt", "rw");
        FileChannel from = file.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/bbb.txt", "rw");
        FileChannel to = file.getChannel();

        long position = 0;
        long size = from.size();
        System.out.println(1);
        from.transferTo(position, size, to);
        System.out.println("2");
        file.close();
        file2.close();
        System.out.println("over");
    }

}
