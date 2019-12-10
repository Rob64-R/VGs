package com.example.games.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.games.entities.Platform;
import com.example.games.service.PlatformService;

@RestController
public class PlatformController {
	@Autowired
	PlatformService service;
	
	@GetMapping("/platform")
	public ResponseEntity<List<Platform>> getPlatforms() {
		return new ResponseEntity<List<Platform>>(service.getPlatforms(), HttpStatus.OK);
	}
	
	@PostMapping("/platform")
	public ResponseEntity<Integer> insertPlatform(@RequestBody Platform platform){
		return new ResponseEntity<Integer>(service.insertGame(platform).getId(), HttpStatus.OK);	
	}
	
	@GetMapping("/platform/{id}")
	public ResponseEntity<Platform> getPlatformById(@PathVariable int id){
		Optional<Platform> test = service.getPlatformById(id);
		if (!test.isPresent()) {
			return new ResponseEntity<Platform>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Platform>(test.get(), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/platform/{id}")
	public ResponseEntity<Platform> deletePlatform(@PathVariable int id){
		Optional<Platform> test = service.getPlatformById(id);
		if (!test.isPresent()) {
			return new ResponseEntity<Platform>(HttpStatus.NOT_FOUND);
		}
		else {
			service.deletePlatform(id);
			System.out.println("Deleted " + service.getPlatformById(id));
			return new ResponseEntity<Platform>(HttpStatus.OK);
		}
		
	}
}
