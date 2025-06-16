package com.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.service.KafkaService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
	private KafkaService Service;
	
	public KafkaController(KafkaService service) {	
		this.Service = service;
	}
	
	  @PostMapping("/publish")
	    public String publish(@RequestParam(name = "message") String message) {
		  	logger.info("Kafka test log with valuetest123");
	    	Service.send(message);
	    	logger.info("Message Successfully Send to the kafka : {}", message);
	        return "Message sent: " + message;
	    }
	  
	    @GetMapping("/test/2")
	    public void testpublish() {
		  	logger.info("Kafka test log with valuetest123");
	    }
}
