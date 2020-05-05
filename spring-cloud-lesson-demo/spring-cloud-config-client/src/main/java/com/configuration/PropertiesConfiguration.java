package com.configuration;

import java.io.IOException;
import java.util.Properties;

public class PropertiesConfiguration {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", "jack");
        properties.setProperty("age", "30");
        properties.storeToXML(System.out, "UTF-8");
    }
}
