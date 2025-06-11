package com.game.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topic = "test-topic";
	
    public void send(String message) {
        kafkaTemplate.send(topic, message);
    }
	
	public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	@KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}
