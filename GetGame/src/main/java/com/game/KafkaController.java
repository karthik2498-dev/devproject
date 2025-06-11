package com.game;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.service.KafkaService;

@RestController("/api/kafka")
public class KafkaController {

	
	private KafkaService Service;
	
	public KafkaController(KafkaService service) {	
		this.Service = service;
	}
	
	  @PostMapping("/publish")
	    public String publish(@RequestParam(name = "message") String message) {
	    	Service.send(message);
	        return "Message sent: " + message;
	    }

	
}
