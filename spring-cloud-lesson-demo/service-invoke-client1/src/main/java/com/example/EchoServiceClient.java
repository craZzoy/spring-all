package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 服务调用类
 * 声明了服务所在应用
 * 定义实际调用方法接口
 */
@FeignClient("service-invoke-provider")
public interface EchoServiceClient {

    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable(value = "message") String message);
}
