package com.base.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedInputStreamTest {

    public static void main(String[] args) throws Exception {

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;

        try {
            inputStream = new BufferedInputStream(new FileInputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/girl.jpeg"));
            outputStream = new BufferedOutputStream(new FileOutputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/girl2.jpeg"));
            int len = -1;
            byte[] bs = new byte[1024];
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
            outputStream.flush();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
