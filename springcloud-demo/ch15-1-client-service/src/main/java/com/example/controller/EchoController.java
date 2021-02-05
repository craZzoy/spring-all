package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author : 郑玮泽
 * @Date : 16:24 2021/2/5
 */
@RestController
public class EchoController {

    @GetMapping("/provider/test")
    public String test(HttpServletRequest request) {
        System.out.println("---success access provider service---");
        return "success access provider service!";
    }

}
