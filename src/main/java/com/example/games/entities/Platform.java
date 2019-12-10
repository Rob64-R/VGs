package com.example.games.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
//@JsonFormat(shape=JsonFormat.Shape.ARRAY)
//@JsonDeserialize(as = Platform.class)
@Table(name = "platform")
public class Platform {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int platform_id;
	@Column
	private String name;
	@Column
	private int year;
	@Column
	private double price;

	public int getId() {
		return platform_id;
	}

	public void setId(int id) {
		this.platform_id = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
