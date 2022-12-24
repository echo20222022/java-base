package com.base.nio.we;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

public class SocketChannelTest {

    @Test
    public void serverSocketChannel() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));
        //设置为非阻塞，否则无法使用Selector
        serverSocketChannel.configureBlocking(false);

        //创建一个Selector
        Selector selector = Selector.open();
        //注册事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("启动成功");
        while (true) {
           int eventLen =  selector.select(3000);
            System.out.println("eventLen →" + eventLen);
           if (eventLen > 0) {
               //得到就绪的channel
               Set<SelectionKey> selectionKeys = selector.selectedKeys();
               for (SelectionKey selectionKey : selectionKeys) {
                   if (selectionKey.isAcceptable()) {
                       //收到连接后，重新设置read事件
                       SocketChannel socketChannel = serverSocketChannel.accept();
                       socketChannel.configureBlocking(false);
                       socketChannel.register(selector, SelectionKey.OP_READ);

                       ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                       System.out.println("发送欢迎语");
                       byteBuffer.put("欢迎光临！".getBytes(StandardCharsets.UTF_8));
                       byteBuffer.flip();
                       socketChannel.write(byteBuffer);

                   } else if (selectionKey.isReadable()) {
                       //如果是可读事件，就读取数
                       SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                       ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                       socketChannel.read(byteBuffer);
                       byteBuffer.flip();
                       byte[] bs = new byte[byteBuffer.limit()];
                       byteBuffer.get(bs);
                       System.out.println(new String(bs));

                       //重新注册事件
                       socketChannel.register(selector, SelectionKey.OP_READ);
                   }
               }
           }
        }
    }

    @Test
    public void socketChannel() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8000));

        new Thread(){
            @Override
            public void run() {
                try {
                    Selector selector = Selector.open();
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    while (true) {
                        int eventLen = selector.select(3000);
                        if (eventLen > 0) {
                            Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            for (SelectionKey selectionKey : selectionKeys) {
                                SocketChannel sc =  (SocketChannel) selectionKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                sc.read(byteBuffer);
                                byte[] bs = new byte[byteBuffer.limit()];
                                byteBuffer.get(bs);
                                System.out.println(new String(bs, 0, bs.length, "UTF-8"));

                                sc.register(selector, SelectionKey.OP_READ);
                            }
                        }
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
        System.out.println("启动了监听线程，请输入：");
        Scanner scanner = new Scanner(System.in);
        System.out.println("aaaa");
        while (scanner.hasNext()) {
            String line = scanner.next();
            socketChannel.write(Charset.forName("UTF-8").encode(line));
        }

    }

}
