package com.example.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.games.entities.Game;
import com.example.games.entities.Platform;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
