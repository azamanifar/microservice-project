package com.example.product_service.controller;

import com.example.product_service.kafka.ProductProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final ProductProducer productProducer;

    public KafkaController(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        productProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully: " + message);
    }
}
