package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Description:
 * @Author : 郑玮泽
 * @Date : 16:38 2021/2/5
 */
@RestController
public class EchoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        System.out.println("---success access test method---");
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String s = headerNames.nextElement();
            System.out.println(s + ": " + request.getHeader(s));
        }
        return "success access test method!";
    }

    /**
     * 请求服务提供者
     * @return
     */
    @RequestMapping("/accessProvider")
    public String accessProvider(HttpServletRequest request){
        final String forObject = restTemplate.getForObject("http://provider-service/provider/test", String.class);
        return forObject;
    }

}
