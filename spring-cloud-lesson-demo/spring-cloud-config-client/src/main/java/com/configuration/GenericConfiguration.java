package com.configuration;

import java.util.Map;

public class GenericConfiguration {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        //不加入需加入jdk参数：-Duser.age=25，返回0
        System.out.println(Integer.getInteger("user.age", 0));

        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
        System.out.println(System.getenv("JAVA8_HOME"));


    }
}
