package com.sapient.swapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.starwars.api.StarWarsApplication;
import com.starwars.api.models.StartWarsTypeInfo;
import com.starwars.api.models.SwapiModelEnum;
import com.starwars.api.service.StarWarsTypeInfoService;
import com.starwars.api.service.StarwarException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = StarWarsApplication.class)
public class SwapiApplicationTests {

	@Autowired
	StarWarsTypeInfoService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPlanets() throws StarwarException {

		StartWarsTypeInfo info = service.getTypeInfo(SwapiModelEnum.VEHICLES, null);
		Assert.assertEquals("Sand Crawler", info.getName());
		Assert.assertEquals(39, info.getCount());
		Assert.assertEquals(2, info.getFilmList().size());

		StartWarsTypeInfo info1 = service.getTypeInfo(SwapiModelEnum.VEHICLES, "T-16");
		Assert.assertEquals("T-16 skyhopper", info1.getName());
		Assert.assertEquals(1, info1.getCount());
		Assert.assertEquals(1, info1.getFilmList().size());
		// T-16 skyhopper
	}

}
