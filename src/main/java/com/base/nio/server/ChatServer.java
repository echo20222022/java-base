package com.base.nio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

public class ChatServer {

    public static void main(String[] args) throws Exception {
        startup();
    }

    public static void startup() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for (SelectionKey key : selectionKeySet) {
                selectionKeySet.remove(key);
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //等待读取操作就绪
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    socketChannel.write(Charset.forName("UTF-8").encode("欢迎进入聊天室！"));
                    System.out.println("有客户的连接");
                } else if (key.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    //读取消息
                    int read = socketChannel.read(byteBuffer);
                    String msg = "";
                    if (read > 0) {
                        byteBuffer.flip();
                        msg += Charset.forName("UTF-8").decode(byteBuffer);
                    }

                    System.out.println("客户端有消息发过来：" + msg);
                    //重新注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    //如果消息不为空，把消息转发给其他客户端
                    if (msg.length() > 0) {
                        //获取所有已经接入的客户端
                        Set<SelectionKey> targetKeys = selector.selectedKeys();
                        for (SelectionKey otherKey : targetKeys) {
                           SocketChannel target = (SocketChannel) otherKey.channel();
                           //排除当前客户端
                            if (target != socketChannel) {
                                target.write(Charset.forName("UTF-8").encode(msg));
                            }
                        }
                    }
                }

                //selectionKeySet.remove(key);
            }
        }
    }
}
