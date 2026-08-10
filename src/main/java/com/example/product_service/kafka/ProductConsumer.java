package com.example.product_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @KafkaListener(
            topics = KafkaTopicConfig.PRODUCT_TOPIC,
            groupId = "product-group"
    )
    public void consume(String message) {
        System.out.println("Message received from Kafka: " + message);
    }}
