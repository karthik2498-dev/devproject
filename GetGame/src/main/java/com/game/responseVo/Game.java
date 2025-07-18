package com.game.responseVo;

import org.springframework.stereotype.Component;

@Component
public class Game {
	private String name;
	private int appId;
    private int players;
    
    
    public Game(String name, int appId, int players) {
  		this.name = name;
  		this.appId = appId;
  		this.players = players;
  	}
    
    
	public Game() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getPlayers() {
		return players;
	}
	public void setPlayers(int players) {
		this.players = players;
	}

}
