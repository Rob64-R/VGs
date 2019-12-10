package com.example.games.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.games.dao.PlatformRepository;
import com.example.games.entities.Platform;

@Service
public class PlatformService {
	
	@Autowired
	PlatformRepository repository;
	
	public List<Platform> getPlatforms() {
		return repository.findAll();
	}
	
	@Transactional
	public Optional<Platform> getPlatformById(Integer id){
		return repository.findById(id);
		
	}
	
	@Transactional
	public Platform insertGame(Platform platform) {
		return repository.save(platform);
	}
	
	@Transactional
	public void deletePlatform(Integer id) {
		repository.deleteById(id);
	}
}
