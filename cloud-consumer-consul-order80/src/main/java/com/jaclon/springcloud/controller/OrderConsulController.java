package com.jaclon.springcloud.controller;

import com.jaclon.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author jaclon
 * @date 2020/3/14
 */
@RestController
public class OrderConsulController {

    public static final String PAYMENT_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String create(Payment payment) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }
}
