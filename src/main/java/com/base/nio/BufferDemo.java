package com.base.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferDemo {

    @Test
    public void test1() throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/aaa.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int bs = fileChannel.read(byteBuffer);
        if (bs != -1) {
            byteBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(byteBuffer));
            byteBuffer.clear();
        }
        file.close();
    }
}
