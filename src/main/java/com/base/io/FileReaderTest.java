package com.base.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderTest {

    public static void main(String[] args) throws Exception {

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt"));
            fileWriter = new FileWriter(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见1.txt"));
            int len = -1;
            char[] chars = new char[1024];
            while ((len = fileReader.read(chars)) != -1) {
                //System.out.println(new String(chars));
                fileWriter.write(chars, 0, len);
            }
        }catch (Exception ex) {

        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
