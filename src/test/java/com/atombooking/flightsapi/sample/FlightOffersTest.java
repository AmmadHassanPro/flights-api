package com.atombooking.flightsapi.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.service.FlightOffersService;

@SpringBootTest
public class FlightOffersTest {
	
	@Autowired
	FlightOffersService service;
	
	@Autowired
	WebClient client;
	
	@Test
	public void testResponse() {
		FlightOffersResponse resp = service.getFlightOffers("SYD", "BKK", LocalDate.parse("2023-07-10"),  Optional.of(LocalDate.parse("2023-07-15")), 1 , false);
		System.out.println(resp);
	}

	
	@Test
	public void testDirect() {
	
		WebClient.ResponseSpec responseSpec = client.get()
			    .uri("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate="+LocalDate.now().plusDays(5).format(DateTimeFormatter.ISO_LOCAL_DATE) +"&returnDate="+LocalDate.now().plusDays(10).format(DateTimeFormatter.ISO_LOCAL_DATE)+"&adults=1")
			    .accept(MediaType.APPLICATION_JSON)
			    .retrieve();
		assertEquals(responseSpec.toBodilessEntity().block().getStatusCode(),HttpStatus.OK);
	}
}
