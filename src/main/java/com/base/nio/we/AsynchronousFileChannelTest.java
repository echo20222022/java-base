package com.base.nio.we;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelTest {

    @Test
    public void read() throws Exception {
        //初见一个异步channel
        Path path = Paths.get("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long position = -1;
        int len = 0;
        while (true) {
            Future<Integer> future = asynchronousFileChannel.read(byteBuffer, position + 1);
            len = future.get();
            if (len == -1) {
                break;
            }
            byteBuffer.flip();
            byte[] bs = new byte[len];
            byteBuffer.get(bs);
            System.out.println(new String(bs, 0, len, "UTF-8"));
            byteBuffer.clear();
            position = position + len;
        }
    }

    @Test
    public void write() throws Exception {
        //初见一个异步channel
        Path path = Paths.get("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/fff.txt");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();

        Future<Integer> future = asynchronousFileChannel.write(byteBuffer, 0);
        int len = future.get();
        byteBuffer.clear();
        System.out.println("write → " + len);
    }

    @Test
    public void read2() throws Exception {
        //初见一个异步channel
        Path path = Paths.get("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //会自动处理，直到数据读取完成
        asynchronousFileChannel.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bs = new byte[attachment.limit()];
                attachment.get(bs);
                System.out.println(new String(bs));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    @Test
    public void write2() throws Exception {
        //初见一个异步channel
        Path path = Paths.get("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/fff.txt");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world222".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();

        asynchronousFileChannel.write(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bs = new byte[attachment.limit()];
                attachment.get(bs);
                System.out.println("write completed, date → " + new String(bs, 0, attachment.limit()));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
