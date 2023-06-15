package com.atombooking.flightsapi.service.impl;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.mocks.LocationApiMockReponse;
import com.atombooking.flightsapi.response.locationapi.LocationAPIConvResp;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
@SpringBootTest
class CityAirportServiceImplTest {
	@Autowired
	WebClient client;
	@Value("${amadeus.airportAndCityEndpoint}")
	String airportAndCityEndpoint;
	static CityAirportServiceImpl obj;
	static MockWebServer mockBackEnd;
	
	@BeforeEach 
	public void init() throws IOException{
		mockBackEnd = new MockWebServer();

		mockBackEnd.start();
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort()) + "/";
		obj = new CityAirportServiceImpl(client, baseUrl , airportAndCityEndpoint);
	}

	@Test
	void getCityAndAirport() {		   
		setKeywordDispatch("chicago");		
		LocationAPIConvResp resp = obj.getCityAndAirport("chicago");
		Assertions.assertTrue(!resp.getData().isEmpty());
	}
	
	@AfterAll
	static void shutDown() throws IOException {
		mockBackEnd.shutdown();
	}
	
	
	private void setKeywordDispatch(String keyword) {
		
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
