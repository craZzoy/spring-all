package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class ServiceInvokeClientBootstrap {

    private final EchoServiceClient serviceClient;

    public ServiceInvokeClientBootstrap(EchoServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceInvokeClientBootstrap.class, args);
    }

    @GetMapping("/call/echo/{message}")
    public String callEcho(@PathVariable("message") String msg) {
        return serviceClient.echo(msg);
    }
}
