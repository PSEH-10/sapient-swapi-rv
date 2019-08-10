package com.starwars.api.service;

import java.io.IOException;

import com.starwars.api.models.StartWarsTypeInfo;
import com.starwars.api.models.SwapiModelEnum;

public interface StarWarsTypeInfoService {
	public StartWarsTypeInfo getTypeInfo(SwapiModelEnum type, String name) throws StarwarException;
}
