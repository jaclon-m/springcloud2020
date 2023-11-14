package com.jaclon.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/11/14 11:21
 */
@RestController
public class ResourceController {
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/port")
    public String getPort() {
        return "server port is :" + serverPort;
    }
}
