package com.atombooking.flightsapi.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.response.locationapi.LocationAPIConvResp;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
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
	void getCityAndAirport() {
		LocationAPIConvResp resp = obj.getCityAndAirport("Chicago");
		System.out.println(resp);
		//fail("Not yet implemented");
	
	}

}
