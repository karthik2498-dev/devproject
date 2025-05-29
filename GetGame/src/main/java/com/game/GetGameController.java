package com.game;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.game.responseVo.Game;
import com.game.service.GameServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class GetGameController {
	
	
	 private final GameServices Service;

	    public GetGameController(GameServices Service) {
	        this.Service = Service;
	    }
	
	@GetMapping
	@Tag(name = "Get Top Games", description = "Fetches a list of top most played games on Steam")
	public String GetGameList(@RequestParam("Id") String Id) throws JsonMappingException, JsonProcessingException {
		
		String result=Service.MostPlayGame(Id);
		return result;
	}
	
	@PutMapping
	public String SaveGameList()
	{
		return null;
	}

}
