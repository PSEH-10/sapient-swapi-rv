package com.starwars.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starwars.api.client.swapi.StarWarsAPIClient;
import com.starwars.api.client.swapi.StarWarsConstants;
import com.starwars.api.models.Film;
import com.starwars.api.models.StartWarsTypeInfo;
import com.starwars.api.models.SwapiModelEnum;

@Service
public class StartWarsTypeInfoServiceImpl implements StarWarsTypeInfoService{
	
	Map<SwapiModelEnum, List<Film>> filmMap = new ConcurrentHashMap<SwapiModelEnum, List<Film>>();
	private static final Logger LOGGER = LoggerFactory.getLogger(StartWarsTypeInfoServiceImpl.class);
	public StartWarsTypeInfo getTypeInfo(SwapiModelEnum type, String name) throws StarwarException {
		LOGGER.info("Executing type inf for {}" ,type.getEntityName());
		JsonObject result = null;
		try {
			result = StarWarsAPIClient.executeGet(getURL(type, name));
		} catch (IOException e) {
			LOGGER.error("Exception while executing the fetch action for : {} " , type.getEntityName(), e);
			throw new StarwarException();
		}
		StartWarsTypeInfo info = getTypeInfoFromResult(result,type);
		return info;
		
	}
	
	private StartWarsTypeInfo getTypeInfoFromResult(JsonObject result,SwapiModelEnum typeEnum) throws StarwarException {
		StartWarsTypeInfo info = new StartWarsTypeInfo();
		info.setCount(result.get("count").getAsInt());
		JsonArray results = result.getAsJsonArray("results");
		if(results!=null) {
			JsonObject obj = results.get(0).getAsJsonObject();
			JsonElement nameAtribute = obj.get(typeEnum.getNameAttr());
			info.setName(nameAtribute.getAsString());
			if(typeEnum !=SwapiModelEnum.FILMS) {
				List<Film> filmList = new ArrayList<Film>();
				//CACHE
				if(filmMap.containsKey(typeEnum)) {
					filmList.addAll(filmMap.get(typeEnum));
				} else {
					JsonArray films = obj.getAsJsonArray("films");
					Gson gson = new Gson();
					for(JsonElement element : films) {
						String filmURL = element.getAsString();
						JsonObject filmObj;
						try {
							filmObj = StarWarsAPIClient.executeGet(filmURL);
						} catch (IOException e) {
							throw new StarwarException();
						}
						Film film = gson.fromJson(filmObj, Film.class);
						filmList.add(film);
					}
					//Not working
					//filmMap.put(typeEnum, filmList);
				}
				info.setFilmList(filmList);
			}
		}
		
		

		info.setType(typeEnum);
		return info;
	}

	public String getURL(SwapiModelEnum type, String name) {
		String url = null;
		if (name == null) {
            url = StarWarsConstants.BASE_URL + type.getEntityName() + "/";
        } else {
            url = StarWarsConstants.BASE_URL + type.getEntityName() + "/?search=" +name;
        }
		return url;
	}

}
