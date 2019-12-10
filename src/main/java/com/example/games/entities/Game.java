package com.example.games.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int game_id;
	@Column
	private String name;
	@Column
	private int year;
	@Column
	private String genre;	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	name = "game_platform", 
	joinColumns = {@JoinColumn(name = "game_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "platform_id") }
	)
	private List<Platform> platform;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getId() {
		return game_id;
	}

	public void setId(int id) {
		this.game_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Platform> getPlatform() {
		return platform;
	}

	public void setPlatform(List<Platform> platform) {
			this.platform = platform;
	}
	

}
