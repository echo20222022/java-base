package com.base.io;

import java.io.*;

public class ObjectStreamTest {

    public static void main(String[] args) throws Exception {
        ObjectStreamTest.out();
        ObjectStreamTest.in();
    }

    public static void out() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/person.obj"));
        Person person = new Person("zhangsan", 10);
        oos.writeObject(person);
        oos.flush();
        oos.close();
    }

    public static void in() throws Exception {
        ObjectInputStream ooi = new ObjectInputStream(new FileInputStream("/Users/dongzhang/develop/workspace/echo20222022/java-base/src/main/resources/person.obj"));
        Person person = (Person)ooi.readObject();
        ooi.close();
        System.out.println(person);
    }

    public static class Person implements Serializable {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}



