package com.base.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AAAA {
    public static void main(String[] args) throws Exception {

        Object
       int[] arr = {};
      // arr.length;
        ArrayList arrayList = new ArrayList();
        double a = 23.6;
        System.out.println(a + 100);

        String url= "http://aaa?name=zhangsan&age=23";
        System.out.println(getQueryString(url, "age", "abc"));
        /*URL url1 = new URL(url);
        String query = url1.getQuery();
        System.out.println(query);

        int startIdx = query.indexOf("age");
        System.out.println(query.substring(startIdx));
        int endIdx = (query.substring(startIdx)).indexOf("&") + startIdx;
        String[] arra = query.substring(startIdx, endIdx).split("=");
        System.out.println(arra.length);
        System.out.println(arra[0] + "," + arra[1]);
        System.out.println(query.substring(query.indexOf("name")));*/

    }

    /**
     * 方法1：正则匹配，以往项目中用过的：  String pattern = "(\\?|&){1}#{0,1}" + key + "=[a-zA-Z0-9]*(&{1})";
     * 方法2：字符串截取
     * */
    static String getQueryString(String url, String param, String initValue) throws MalformedURLException {
        //如果key为空，返回null
        if (param == null || "".equals(param)) {
            return null;
        }
        URL url1 = new URL(url);
        String queryStr = url1.getQuery() ;
        //如果参数为空，返回默认值
        if ("".equals(queryStr)) {
            return initValue;
        }
        //加一个&统一后面的操作逻辑
        queryStr = queryStr+ "&";

        int startIdx = queryStr.indexOf(param);
        //没找到对应的key
        if (startIdx == -1) {
            return initValue;
        }
        int endIdx = queryStr.substring(startIdx).indexOf("&") + startIdx;
        String kvStr = queryStr.substring(startIdx, endIdx);
        String[] kvArr = kvStr.split("=");

        if (kvArr.length == 1) {
            return initValue;
        }

        return kvArr[1];
    }
}
