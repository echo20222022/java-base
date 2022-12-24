package com.base.nio.client;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

public class ChatClient {

    public static void main(String[] args) throws Exception{
        startup();
    }

    //@Test
    public static class client1 {
        public static void main(String[] args) throws Exception{
            startup();
        }

    }

    //@Test
    public static class client2 {
        public static void main(String[] args) throws Exception{
            startup();
        }

    }
    public static void startup() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8000));
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        new Thread() {
            @Override
            public void run() {
                try {
                    //super.run();
                    while (selector.select() > 0) {
                        Set<SelectionKey> keySet = selector.selectedKeys();
                        for (SelectionKey key : keySet) {
                            if (key.isReadable()) {
                                SocketChannel socketChannel1 = (SocketChannel) key.channel();
                                //读取消息
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                int len = socketChannel1.read(byteBuffer);
                                if (len > 0) {
                                    byteBuffer.flip();
                                    String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
                                    System.out.println(msg);
                                }
                                //重新祖册到selector上
                                socketChannel1.register(selector, SelectionKey.OP_READ);
                            }
                        }
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.next();
            if (msg.length() > 0) {
                socketChannel.write(Charset.forName("UTF-8").encode(msg));
            }
        }

    }
}
