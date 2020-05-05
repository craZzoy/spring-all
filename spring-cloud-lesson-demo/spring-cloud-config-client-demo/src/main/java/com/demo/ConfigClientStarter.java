package com.demo;

import com.demo.health.MyHealthIndicator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
public class ConfigClientStarter {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientStarter.class, args);
    }

    private final Environment environment;
    private final ContextRefresher refresher;

    public ConfigClientStarter(Environment environment, ContextRefresher refresher) {
        this.environment = environment;
        this.refresher = refresher;
    }

    /**
     * 通过refresher定时刷新配置
     */
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {
        Set<String> strings = refresher.refresh();
        strings.stream().forEach(s -> {
            System.err.printf("[Thread :%s] 当前配置已经更新，key：%s，value：%s \n",
                    Thread.currentThread().getName(),
                    s,
                    environment.getProperty(s));
        });

    }

    @Bean
    public MyHealthIndicator indicator() {
        return new MyHealthIndicator();
    }
}
