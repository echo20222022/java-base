package com.base.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

public class PipeTest {

    @Test
    public void test() throws Exception {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello pipe".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();

        sinkChannel.write(byteBuffer);

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer sourceBuffer = ByteBuffer.allocate(1024);
        int len =  sourceChannel.read(sourceBuffer);
        System.out.println(new String(sourceBuffer.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }

}
