package com.atombooking.flightsapi.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.reactive.function.client.WebClient;
@SpringBootTest
class CityAirportServiceImplTest {
	
	@Autowired
	CityAirportServiceImpl obj;
	
	@Autowired
	WebClient client;
	@BeforeTestClass
	public void init() {
		
	}

	@Test
	void testGetCity() {
		//fail("Not yet implemented");

	
	}

	@Test
	void testGetAirport() {
		fail("Not yet implemented");
	}

}
