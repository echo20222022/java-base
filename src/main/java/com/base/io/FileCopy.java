package com.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = null;// new FileInputStream(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/girl.jpeg"));
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/girl.jpeg"));
            fileOutputStream = new FileOutputStream(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/girl1.jpeg"));

            byte[] bs = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(bs)) != -1) {
                fileOutputStream.write(bs, 0, len);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}
