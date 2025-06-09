package com.game.repo;

import org.springframework.stereotype.Repository;

import com.game.model.GameList;
import com.game.responseVo.Game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GameRepo extends JpaRepository<GameList,Long> {
	
	Game game= new Game();

	@Query("SELECT new com.game.responseVo.Game(g.name,g.appId,g.players)"+"FROM GameList g WHERE g.players = (SELECT MAX(g2.players) FROM GameList g2)")
    Game findGameWithMostPlayers();

}
