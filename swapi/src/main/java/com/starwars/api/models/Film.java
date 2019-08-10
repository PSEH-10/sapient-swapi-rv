package com.starwars.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Film {
	
	String title;

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

}
