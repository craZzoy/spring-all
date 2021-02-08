package com.example.controller;

import com.example.util.JwtUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Description: token controller
 * @Author : 郑玮泽
 * @Date : 16:58 2021/2/5
 */
@RestController
public class TokenController {

    @GetMapping("/getToken/{name}")
    public String get(@PathVariable(value = "name") @Validated @NotNull String name){
        return JwtUtil.generateToken(name);
    }

}
