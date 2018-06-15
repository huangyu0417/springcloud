package com.bootcloud.eurekaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaproducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaproducerApplication.class, args);
    }

    @GetMapping("/hello")
    public String info() {
        return "hello world";
    }
}
