package com.example.games.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.games.dao.GameRepository;
import com.example.games.dao.PlatformRepository;
import com.example.games.entities.Game;
import com.example.games.entities.Platform;

@Service
public class GameService {
	
	@Autowired
	GameRepository repository;
	
	@Autowired
	PlatformRepository p_repository;
	
	public List<Game> getGames() {
		return repository.findAll();
	}
	
	@Transactional
	public Optional<Game> getGameById(Integer id){
		return repository.findById(id);
		
	}
	
	@Transactional
	public Game insertGame(Game game) {
		List<Platform> pList = game.getPlatform();
		for(Platform p : pList) {
			Optional<Platform> optional = p_repository.findById(p.getId());
			if(!optional.isPresent()) {
				p_repository.save(p);
			}
		}
		return repository.save(game);
	}
	
	@Transactional
	public void deleteGame(Integer id) {
		repository.deleteById(id);
	}

}
