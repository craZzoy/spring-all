package com.example.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author : 郑玮泽
 * @Date : 16:45 2021/2/5
 */
@Configuration
public class CommonConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        final RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getInterceptors().add();
        return restTemplate;
    }

}
