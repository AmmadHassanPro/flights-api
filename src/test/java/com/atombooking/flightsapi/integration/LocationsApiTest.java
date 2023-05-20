package com.atombooking.flightsapi.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
/*
 * 
 * This test will hit the locations endpoint to see if we are getting a response
 */
@SpringBootTest
class LocationsApiTest {

	@Autowired
	WebClient client;
	
	@Test
	void test() {
		WebClient.ResponseSpec responseSpec = client.get()
			    .uri("https://test.api.amadeus.com/v1/reference-data/locations?subType=CITY,AIRPORT&keyword=chicago")
			    .accept(MediaType.APPLICATION_JSON)
			    .retrieve();
		assertEquals(responseSpec.toBodilessEntity().block().getStatusCode(),HttpStatus.OK);
	}

}
