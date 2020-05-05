package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//@EnableEurekaClient
@SpringBootApplication
//@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class EurekaClientBootstrap {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientBootstrap.class, args);
    }

    /**
     * 返回所有服务
     *
     * @return
     */
    @GetMapping("/services")
    public Set<String> getService() {
        return new LinkedHashSet<>(discoveryClient.getServices());
    }

    /**
     * 返回某个服务的所有实例
     *
     * @param serviceName
     * @return
     */
    @GetMapping("/services/{serviceName}")
    public List<ServiceInstance> getServiceInstances(
            @PathVariable(value = "serviceName") String serviceName) {
        return new LinkedList<>(discoveryClient.getInstances(serviceName));
    }

    /**
     * 返回具体某个实例信息
     *
     * @param serviceName
     * @param instanceId
     * @return
     */
    @GetMapping("/services/{serviceName}/{instanceId}")
    public ServiceInstance getInstance(@PathVariable(value = "serviceName") String serviceName,
                                       @PathVariable(value = "instanceId") String instanceId) {
        return getServiceInstances(serviceName)
                .stream()
                .filter(serviceInstance -> instanceId.equals(serviceInstance.getInstanceId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Suck Service Instance"));

    }
}
