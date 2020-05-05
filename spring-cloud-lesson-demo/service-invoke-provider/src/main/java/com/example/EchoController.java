package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    private final Environment environment;

    public EchoController(Environment environment) {
        this.environment = environment;
    }

    /**
     * 获取实际运行的端口
     *
     * @return
     */
    private String getLocalServerPort() {
        return environment.getProperty("local.server.port");
    }

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable(value = "message") String message) {
        System.out.println("i am service provider...");
        return "Echo " + getLocalServerPort() + " " + message;
    }
}
