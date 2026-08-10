package com.example.product_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${app.message}")
    private String message;

    @Value("${app.version}")
    private String version;

    @GetMapping("/product")
    public String product() {
        return message + " - version: " + version;
    }
}