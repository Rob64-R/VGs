package com.example.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.games.entities.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
	
}
