package com.atombooking.flightsapi.service.impl;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.mocks.LocationApiMockReponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import okhttp3.mockwebserver.MockWebServer;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CityAirportServiceImplTest {
	@Autowired
	WebClient client;
	@Value("${amadeus.airportAndCityEndpoint}")
	String airportAndCityEndpoint;
	static CityAirportServiceImpl obj;
	static MockWebServer mockBackEnd;
	static WireMockServer wireMockServer;
	static int port = 8090;
	static String baseUrl = String.format("http://localhost:%s",port) + "/";
	
	@BeforeAll
	public static void init1() throws IOException {
	wireMockServer = new WireMockServer(port);
	wireMockServer.start();
	}	

	@Test
	@Order(1) 
	void getCityAndAirportResponse() {
		
		obj = new CityAirportServiceImpl(client, baseUrl , airportAndCityEndpoint);
		String keyword = "chicago";
		
		wireMockServer.stubFor(
				get(urlEqualTo("/"+airportAndCityEndpoint+keyword)).willReturn(ok().withStatus(200).withBody(LocationApiMockReponse.MOCK_REPONSE).withHeader("Content-Type", "application/json"))
				);
			
		LocationApiDto resp = obj.getCityAndAirport("chicago");
		Assertions.assertTrue(!resp.getData().isEmpty());
	}
	
	@AfterAll
	static void shutDown() throws IOException {
		wireMockServer.shutdown();
	}
	
}
