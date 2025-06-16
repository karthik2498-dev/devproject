package com.game;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.game.responseVo.Game;
import com.game.service.GameServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
public class GetGameController {
	
	 private static final Logger logger = LoggerFactory.getLogger(GetGameController.class);
	
	 private final GameServices Service;

	 public GetGameController(GameServices Service) {
	        this.Service = Service;
	    }

	 @GetMapping("/hello")
	 @Tag(name = "hello", description = "testhello")
	 public String hello() {
	    logger.info("Received request on /hello");
	    return "Hello from Spring Boot!";
	  }
	 
	 
	@GetMapping("/GetGameNameByID")
	@Tag(name = "Get GetGameNameByID", description = "Fetches the GetGameNameByID")
	public String GetGameNameByID(@RequestParam("Id") String Id) throws JsonMappingException, JsonProcessingException {
		
		String result=Service.MostPlayGame(Id);
		return result;
	}
	
	@GetMapping("/GetMostPlayedGame")
	public String GetMostPlayedGame()
	{
		
		 RestTemplate restTemplate = new RestTemplate();
	     String url = "http://localhost:1111/GetTopGamne";
	     ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
	     int result = response.getBody();
	     
	     String url2 = "http://localhost:1111/GetGameNameByID?Id={id}";
	     ResponseEntity<String> response2 = restTemplate.getForEntity(url2, String.class,String.valueOf(result));
	     String name = response2.getBody();
		return name;
	}
	
	@GetMapping("/GetTopGamne")
	@Tag(name = "Get Top Games ID", description = "Fetches the top most played game ID on Steam")
	public int GetTopGamne() throws JsonMappingException, JsonProcessingException {
		
		int appid=Service.MostPlayedgamID();
		return appid;
	}
	
	@PostMapping("/savegame")
	public String savegame(@RequestBody  Game game) {
		String result = Service.savegame(game);
		return result;
	}

}
