package com.starwars.api.models;

import java.util.List;


public class StartWarsTypeInfo {
	
	
	private SwapiModelEnum type;
	private String name;
	private int count;
	private List<Film> filmList;
	
	public SwapiModelEnum getType() {
		return type;
	}
	public void setType(SwapiModelEnum type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Film> getFilmList() {
		return filmList;
	}
	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}


}
