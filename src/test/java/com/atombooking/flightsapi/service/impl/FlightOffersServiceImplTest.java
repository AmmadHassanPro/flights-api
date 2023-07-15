package com.atombooking.flightsapi.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.time.LocalDate;
import java.util.Optional;

import com.atombooking.flightsapi.mocks.FlightOffersServiceMockResponse;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootTest
public class FlightOffersServiceImplTest {	
	static int port = 8090;

	@Autowired
	WebClient client;
	
	FlightOffersServiceImpl service;
	
	@Value("${amadeus.flightOffersEndpoint}") 
	String endpointUrl;
	
	String base = "http://localhost:"+port+"/";
	
	int maxFlights = 1;
	
	static WireMockServer wireMockServer;
	
	@BeforeAll
	public static void init() {
		wireMockServer = new WireMockServer(port);
		wireMockServer.start();
	}
	
	@Test
	public void testgetFlightOffers() {
		
		service = new FlightOffersServiceImpl(client,base,endpointUrl);

		LocalDate dep = LocalDate.now().plusDays(10);
		LocalDate ret = LocalDate.now().plusDays(20); 
		String source = "SYD";
		String dest = "BKK";
		int numOfAdults = 5;
		boolean nonStop = true;
		
		wireMockServer.stubFor(get(urlPathEqualTo("/"+endpointUrl))
				.withQueryParam("originLocationCode", equalTo(source))
				.withQueryParam("destinationLocationCode", equalTo(dest))
				.withQueryParam("departureDate", equalTo(dep.toString()))
				.withQueryParam("adults", equalTo(String.valueOf(numOfAdults)))
				.withQueryParam("max", equalTo(String.valueOf(maxFlights)))
				.withQueryParam("returnDate", equalTo(ret.toString()))
				.withQueryParam("currencyCode", equalTo("USD"))
				.withQueryParam("nonStop", equalTo(String.valueOf(nonStop)))
				.willReturn(ok().withStatus(200).withBody(FlightOffersServiceMockResponse.MOCK_REPONSE).withHeader("Content-Type", "application/vnd.amadeus+json")));
		
		
		FlightOffersResponse resp = service.getFlightOffers(source, dest, dep, Optional.of(ret), numOfAdults, nonStop);	
		
		wireMockServer.verify(getRequestedFor(urlPathEqualTo("/"+endpointUrl))
				.withQueryParam("originLocationCode", equalTo(source))
				.withQueryParam("destinationLocationCode", equalTo(dest))
				.withQueryParam("departureDate", equalTo(dep.toString()))
				.withQueryParam("adults", equalTo(String.valueOf(numOfAdults)))
				.withQueryParam("max", equalTo(String.valueOf(maxFlights)))
				.withQueryParam("returnDate", equalTo(ret.toString()))
				.withQueryParam("currencyCode", equalTo("USD"))
				.withQueryParam("nonStop", equalTo(String.valueOf(nonStop))));
		
	}
	
	@Test
	public void testgetFlightOffersWithoutOptionals() {
		
		service = new FlightOffersServiceImpl(client,base,endpointUrl);

		LocalDate dep = LocalDate.now().plusDays(10);
		LocalDate ret = LocalDate.now().plusDays(20); 
		String source = "SYD";
		String dest = "BKK";
		int numOfAdults = 5;
		boolean nonStop = false;
		
		wireMockServer.stubFor(get(urlPathEqualTo("/"+endpointUrl))
				.withQueryParam("originLocationCode", equalTo(source))
				.withQueryParam("destinationLocationCode", equalTo(dest))
				.withQueryParam("departureDate", equalTo(dep.toString()))
				.withQueryParam("adults", equalTo(String.valueOf(numOfAdults)))
				.withQueryParam("max", equalTo(String.valueOf(maxFlights)))
				.willReturn(ok().withStatus(200).withBody(FlightOffersServiceMockResponse.MOCK_REPONSE).withHeader("Content-Type", "application/vnd.amadeus+json")));
		
		
		FlightOffersResponse resp = service.getFlightOffers(source, dest, dep, Optional.empty(), numOfAdults, nonStop);	
		
		wireMockServer.verify(getRequestedFor(urlPathEqualTo("/"+endpointUrl))
				.withQueryParam("originLocationCode", equalTo(source))
				.withQueryParam("destinationLocationCode", equalTo(dest))
				.withQueryParam("departureDate", equalTo(dep.toString()))
				.withQueryParam("adults", equalTo(String.valueOf(numOfAdults)))
				.withQueryParam("max", equalTo(String.valueOf(maxFlights))));
		
	}
	
	
	
	@AfterAll
	public static void destroy() {
		wireMockServer.stop();
	}
	
}
