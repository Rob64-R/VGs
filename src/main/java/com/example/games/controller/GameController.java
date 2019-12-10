package com.example.games.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.games.entities.Game;
import com.example.games.service.GameService;

@RestController
public class GameController {
	
	@Autowired
	GameService service;
	
	@GetMapping("/game")
	public ResponseEntity<List<Game>> getGames() {
		return new ResponseEntity<List<Game>>(service.getGames(), HttpStatus.OK);
	}
	
	@PostMapping("/game")
	public ResponseEntity<Integer> insertGame(@RequestBody Game game){
		System.out.println("posting");
		return new ResponseEntity<Integer>(service.insertGame(game).getId(), HttpStatus.OK);
		
	}
	
	@GetMapping("/game/{id}")
	public ResponseEntity<Game> getGameById(@PathVariable int id){
		Optional<Game> test = service.getGameById(id);
		if (!test.isPresent()) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Game>(test.get(), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/game/{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable int id){
		Optional<Game> test = service.getGameById(id);
		if (!test.isPresent()) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}
		else {
			service.deleteGame(id);
			System.out.println("Deleted " + service.getGameById(id));
			return new ResponseEntity<Game>(HttpStatus.OK);
		}
		
	}

}
