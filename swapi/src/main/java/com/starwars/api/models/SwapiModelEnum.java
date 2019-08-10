package com.starwars.api.models;

public enum SwapiModelEnum {
	PLANET("planets"),VEHICLES("vehicles"),FILMS("films","title"),PEOPLE("people"),SPECIES("species"),STARSHIPS("starships");

	String entityName = null;
	String nameAttr = "name";
	SwapiModelEnum(String entityName) {
		this.entityName = entityName;
	}
	
	SwapiModelEnum(String entityName, String nameAttr) {
		this.entityName = entityName;
		this.nameAttr = nameAttr;
	}
	public String getEntityName() {
		return entityName;
	}

	public String getNameAttr() {
		return nameAttr;
	}

}
