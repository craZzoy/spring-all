package com.configuration;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.EnvironmentConfiguration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class Test {
    public static void main(String[] args) {
        Configuration envConfig = new EnvironmentConfiguration();
        System.out.println("JAVA_HOME=" + envConfig.getString("JAVA_HOME"));

        PropertySource source = new PropertiesPropertySource("java_system", System.getProperties());
        System.out.println(source.getProperty("file.encoding"));

        int[] is = new int[10];
    }
}
