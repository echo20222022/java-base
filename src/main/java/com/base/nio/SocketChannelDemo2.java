package com.base.nio;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class SocketChannelDemo2 {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        //设置一些tcp参数
        //socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);

        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
        System.out.println(Charset.forName("UTF-8").decode(byteBuffer));

        //System.out.println(new String(bs, "UTF-8"));
    }
}
