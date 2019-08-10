package com.starwars.api.client.swapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class StarWarsAPIClient {

	public static JsonObject executeGet(String uri) throws IOException {

		HttpGet httpGet = new HttpGet(uri);
        HttpClient httpClient = HttpClientBuilder.create().build();
        httpGet.addHeader("accept", "application/json");
        httpGet.addHeader("Content-Type","application/x-www-form-urlencoded");
        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject jsonObject = deserialize(stringBuilder.toString());
        bufferedReader.close();

        return jsonObject;
    }
	
	private static JsonObject deserialize(String json) {
        Gson gson = new Gson();
        JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
        return jsonClass;
    }

}