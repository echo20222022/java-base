package com.base.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamReaderTest {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见.txt"), StandardCharsets.UTF_8));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/傲慢与偏见3.txt"), StandardCharsets.UTF_8));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
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
