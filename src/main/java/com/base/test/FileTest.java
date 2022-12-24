package com.base.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {

        /*
        String fileStr = "/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/logback.xml";
        File file = new File(fileStr);
        //获取文件名称
        System.out.println(file.getName());
        //获取文件的绝对路径
        System.out.println(file.getAbsolutePath());
        //获取文件所在目录
        System.out.println(file.getParent());
        //获取文件长度
        System.out.println(file.length());
        //判断文件是否存在
        System.out.println(file.exists());
        //判断是否是文件
        System.out.println(file.isFile());
        //判断是否是目录
        System.out.println(file.isDirectory());

        //如果文件/目录不存在，创建文件或目录
        if (!file.exists()) {
            file.createNewFile();
            file.mkdir();
        }
*/
        /*String fileStr = "/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/logback.xml";
        String fileStr1 = "/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/logback1.xml";
        FileInputStream fileInputStream = new FileInputStream(new File(fileStr));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileStr1));

        byte[] bs = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(bs)) != -1) {
            fileOutputStream.write(bs);
        }
        fileInputStream.close();
        fileOutputStream.close();*/


    }
}


/*

囡囡呀不要调皮
坐下听听阿婆说
这个季节天气转凉地上雨水多
囡囡呀不要惊慌
过来听听阿婆说
睡个觉雷声过后就能看云朵
囡囡别怕
囡囡别哭
快快睡咯
你静静听首歌
蛐蛐轻些
静静安歇
月儿圆哟
你(ni 一声)乖乖呀抱阿婆
风铃呀轻响鸟儿轻唱远处谁在和
亲了彩虹惊了云朵
我已成归客
囡囡呀你会长大会走很远会觉得累了
只要记得河婆话“阿婆”怎么说
囡囡呀你会困惑
慢些脚步别忘了
慢慢的你会明白丢了的是什么
人生路本就是场获得与失的选择
迷路时想想当年阿婆怎么说
回头看看
雨水过后
云彩很多
来吧 阿婆帮你偷偷摘一朵
等你老了
阿婆走了
你要记得
把(拔)这乡音教会娃儿怎么说

* */