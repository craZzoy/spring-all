package com;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBootCloudClientStarter {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootCloudClientStarter.class);
        application.run(args);
    }

    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLoader implements PropertySourceLocator {

        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("server.port", "9090");
            MapPropertySource mapPropertySource =
                    new MapPropertySource("my-property-source", source);
            return mapPropertySource;
        }
    }

}
