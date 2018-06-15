package com.bootcloud.eurekaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaconsumerApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaconsumerApplication.class, args);
    }

    @GetMapping("/remote")
    public String remoteCall() {
        ServiceInstance service = loadBalancerClient.choose("producer-main");
        String url = "http://" + service.getHost() + ":" + service.getPort() + "/hello";
        return restTemplate.getForObject(url, String.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
