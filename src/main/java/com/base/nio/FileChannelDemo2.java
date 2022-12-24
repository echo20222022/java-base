package com.base.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo2 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/aaa.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String newDAta = "aaaaa";
        byteBuffer.clear();

        //向buffer放入数据
        byteBuffer.put(newDAta.getBytes());
        byteBuffer.flip();
        if (byteBuffer.hasRemaining()) {
            //写入数据
            channel.write(byteBuffer);
        }

        channel.close();

    }
}
