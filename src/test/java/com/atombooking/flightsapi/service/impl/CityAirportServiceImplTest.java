package com.atombooking.flightsapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CityAirportServiceImplTest {
	@Autowired
	WebClient client;
	@Value("${amadeus.airportAndCityEndpoint}")
	String airportAndCityEndpoint;
	static CityAirportServiceImpl obj;
	static MockWebServer mockBackEnd;
	
	@BeforeAll
	public static void init1() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}
	
	@BeforeEach 
	public void init(){
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort()) + "/";
		obj = new CityAirportServiceImpl(client, baseUrl , airportAndCityEndpoint);
	}
	

	@Test
	@Order(1) 
	void getCityAndAirportResponse() {		   
		setDispatcher("chicago");		
		LocationApiDto resp = obj.getCityAndAirport("chicago");
		Assertions.assertTrue(!resp.getData().isEmpty());
	}
	
	@Test
	@Order(2) 
	void getCityAndAirportRequest() throws InterruptedException {
		  RecordedRequest request1 = mockBackEnd.takeRequest();
		  assertEquals(request1.getPath() , "/"+airportAndCityEndpoint+"chicago");
		  assertNotNull(request1.getHeader("Authorization"));
	}
	
	@AfterAll
	static void shutDown() throws IOException {
		mockBackEnd.shutdown();
	}
	
	
	private void setDispatcher(String keyword) {
		
		final Dispatcher dispatcher = new Dispatcher() {

		    @Override
		    public MockResponse dispatch (RecordedRequest request) throws InterruptedException {

		        if (request.getPath().equals("/"+airportAndCityEndpoint+keyword)) {
		                return new MockResponse()
		  				      .setBody(LocationApiMockReponse.MOCK_REPONSE)
						      .addHeader("Content-Type", "application/json").setResponseCode(200);
		        }
		        return new MockResponse().setResponseCode(404);
		    }
		};
		
		mockBackEnd.setDispatcher(dispatcher);
	}
}
