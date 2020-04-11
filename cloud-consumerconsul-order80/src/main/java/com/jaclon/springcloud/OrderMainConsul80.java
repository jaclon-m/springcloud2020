package com.jaclon.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jaclon
 * @date 2020/3/14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMainConsul80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainConsul80.class);
    }
}
