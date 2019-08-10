package com.starwars.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.api.models.StartWarsTypeInfo;
import com.starwars.api.models.SwapiModelEnum;
import com.starwars.api.service.StarWarsTypeInfoService;
import com.starwars.api.service.StarwarException;

@RestController
@RequestMapping(path = "/v1/swapi")
public class StarWarsGenericController {

	@Autowired
	StarWarsTypeInfoService service;

	@GetMapping
	public ResponseEntity<StartWarsTypeInfo> getTypeInformation(@RequestParam String type,
			@RequestParam(required = false) String name) throws StarwarException {
		SwapiModelEnum typeEnum = SwapiModelEnum.valueOf(type);
		if (typeEnum == null) {
			ResponseEntity.badRequest();
		}

		StartWarsTypeInfo info = null;
		info = service.getTypeInfo(typeEnum, name);
		return ResponseEntity.ok(info);

	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An unexpected error occured while processing your request")
	@ExceptionHandler(StarwarException.class)
	public void exceptionHandler() {
	}

}