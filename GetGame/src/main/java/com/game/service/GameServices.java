package com.game.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.model.GameList;
import com.game.repo.GameRepo;
import com.game.responseVo.Game;

@Service
public class GameServices {
	
	
	private GameRepo gamerepo;

	 public GameServices(GameRepo gamerepo, GameList gamelist) {
		this.gamerepo=gamerepo;
	}


	 private static final String API_URL1 = "https://api.steampowered.com/ISteamChartsService/GetMostPlayedGames/v1/";
	 private static final String API_URL2 ="https://store.steampowered.com/api/appdetails?appids=";
	 
	 	@Scheduled(fixedRate = 120000)
	    public void getTopGames() {
	 		LocalTime currentTime = LocalTime.now();
	        RestTemplate restTemplate = new RestTemplate();
	        Map response = restTemplate.getForObject(API_URL1, Map.class);
	        List<Map<String, Object>> gamesRaw = (List<Map<String, Object>>) ((Map) response.get("response")).get("ranks");

	        System.out.println("Size of the list is "+ gamesRaw.size());
	        
	        List<Game> games = new ArrayList<>();
	        for (Map<String, Object> gameData : gamesRaw) {
	            Game game = new Game();
	            game.setAppId((int) gameData.get("appid"));
	            game.setPlayers((int) gameData.get("rank"));
	            game.setName("AppID: " + game.getAppId()); // You can resolve actual names with another API call
	            SaveGameList(game);
	        }
	       
	    }
	 	
	 	public String SaveGameList(Game game){
			   
			   try {
				   GameList gamelist = new GameList(); // create new instance
			        gamelist.setAppId(game.getAppId());
			        gamelist.setName(game.getName());
			        gamelist.setPlayers(game.getPlayers());
			        gamerepo.save(gamelist);
			   return "SUCCESS";
			   }catch(Exception e) {
				   return "Execptiopn : "+e.getMessage();
			   }
			  
		   }
   
	   public String MostPlayGame(String appid) throws JsonMappingException, JsonProcessingException {
	   		RestTemplate restTemplate = new RestTemplate();
	   		ObjectMapper objectMapper = new ObjectMapper();
	   		
	   		  String url = API_URL2+appid;
	   		
	   		 String response = restTemplate.getForObject(url, String.class);
             JsonNode root = objectMapper.readTree(response);
             JsonNode appData = root.path(appid);
             
             if (appData.path("success").asBoolean()) {
                 return appData.path("data").path("name").asText();
             } else {
                 return "AppID not found or invalid.";
             }
             
  }
	   
	   public int MostPlayedgamID() {   
		   Game game=(Game) gamerepo.findGameWithMostPlayers();
		   int result=game.getAppId();
		   return result;	   
	   }
	   
	   public String savegame(Game game) {
		   try {
		   GameList list= new GameList();
		   list.setAppId(game.getAppId());
		   list.setName(game.getName());
		   list.setPlayers(game.getPlayers());
		   gamerepo.save(list);
		   }
		   catch(Exception e) {
			   e.getMessage();
			   return "failed to save";
		   }
		   return "successfully updated";
	   }
	   
	   @KafkaListener(topics = "test-topic", groupId = "my-group")
	    public void listen(String message) {
	        System.out.println("Received message: " + message);
	    }

	
}
