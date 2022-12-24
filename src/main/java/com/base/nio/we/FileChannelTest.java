package com.base.nio.we;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelTest {

    @Test
    public void read() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        int len = 0;
        while (len != -1) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            len = fileChannel.read(byteBuffer);
            if (len == -1) {
                break;
            }
            byteBuffer.flip();
            byte[] bs = new byte[1024];
            byteBuffer.get(bs);
            System.out.println(new String(bs,0, len, "UTF-8"));
            byteBuffer.clear();
        }
    }

    @Test
    public void write() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/ddd.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("这是一条测试语句".getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

    @Test
    public void transfer() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/ddd.txt", "rw");
        FileChannel fileChannel1 = randomAccessFile.getChannel();

        RandomAccessFile randomAccessFile2 = new RandomAccessFile("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/eee.txt", "rw");
        FileChannel fileChannel2 = randomAccessFile2.getChannel();

        long position = 0;
        long size = fileChannel1.size();
        fileChannel1.transferTo(position, size, fileChannel2);

        fileChannel1.close();
        fileChannel2.close();
    }
}
