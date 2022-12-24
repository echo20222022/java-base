package com.base.io;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        //加载
        properties.load(new FileInputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/p.properties"));
        System.out.println(properties.getProperty("id"));
        properties.setProperty("name", "lisi");
        //写出
        properties.store(new FileWriter("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/p1.properties"), "");

    }
}
