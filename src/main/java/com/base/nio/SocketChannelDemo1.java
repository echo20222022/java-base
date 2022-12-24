package com.base.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketChannelDemo1 {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello world".getBytes());
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(false);

        //设置一些tcp参数
        //serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
        while (true) {
           SocketChannel socketChannel =  serverSocketChannel.accept();
           if (socketChannel == null) {
               System.out.println("null");
               TimeUnit.SECONDS.sleep(2);
           } else {
               SocketAddress address = socketChannel.socket().getRemoteSocketAddress();
               System.out.println(address);
               byteBuffer.rewind();
               socketChannel.write(byteBuffer);
               socketChannel.close();
           }

        }
    }
}
