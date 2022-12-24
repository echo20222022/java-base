package com.base.nio.server;

import com.base.io.ObjectStreamTest;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class AIOChatServer {
    public static void main(String[] args) throws Exception {

        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));

        //定义一个异步处理的回调函数
        CompletionHandler<AsynchronousSocketChannel, Object> handler = new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                //继续监听下一个请求
                serverSocketChannel.accept(attachment, this);

                try {
                    System.out.println("request →" + result.getRemoteAddress().toString());
                    //向客户端发送数据
                    result.write(ByteBuffer.wrap("欢迎光临!".getBytes(StandardCharsets.UTF_8)));
                    ByteBuffer read = ByteBuffer.allocate(1024);
                    //阻塞等待客户端发来消息
                    result.read(read).get();
                    System.out.println(new String(read.array(), 0, read.limit()));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        };

        serverSocketChannel.accept(null, handler);
        //防止异步退出
        TimeUnit.DAYS.sleep(Integer.MAX_VALUE);
    }
}
