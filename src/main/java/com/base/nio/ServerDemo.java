package com.base.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

public class ServerDemo {

    public static class Server {

        public static void main(String[] args) throws Exception {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8080));

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey key : keySet) {
                    if (key.isAcceptable()) {
                        System.out.println("accept");
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        System.out.println("readable");
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        ByteBuffer bf = ByteBuffer.allocate(1024);
                        socketChannel.read(bf);
                        bf.flip();
                        //System.out.println(Charset.forName("UTF-8").decode(byteBuffer));
                        System.out.println(new String(byteBuffer.array(), 0, bf.limit()));
                    } else if (key.isConnectable()) {
                        System.out.println("connection");
                    }
                    keySet.remove(key);
                }
            }
        }

    }

    public static class Client {
        public static void main(String[] args) throws Exception {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8080));

            socketChannel.configureBlocking(false);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(new Date().toString().getBytes());
            byteBuffer.flip();

            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
