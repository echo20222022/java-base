package com.base.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int readByte = fileChannel.read(byteBuffer);
        while (readByte != -1) {
            //System.out.println("读取了：" + readByte);
            //指针复位
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println(byteBuffer.get());
            }
            byteBuffer.clear();
            readByte = fileChannel.read(byteBuffer);
        }
        file.close();
    }

}
