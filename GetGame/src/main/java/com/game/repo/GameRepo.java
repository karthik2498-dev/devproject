package com.game.repo;

import org.springframework.stereotype.Repository;

import com.game.model.GameList;
import com.game.responseVo.Game;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GameRepo extends JpaRepository<GameList,Long> {

	void save(Game game);

}
