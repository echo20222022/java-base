package com.base.io;

import java.io.*;

public class BufferedReaderTest {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt")));
            bufferedWriter = new BufferedWriter(new BufferedWriter(new FileWriter("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见2.txt")));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
