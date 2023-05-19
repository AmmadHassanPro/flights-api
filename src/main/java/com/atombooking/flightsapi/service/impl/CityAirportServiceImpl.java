package com.atombooking.flightsapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.service.CityAirportService;

@Service
public class CityAirportServiceImpl implements CityAirportService {
	
	@Autowired
	WebClient client;
	
	@Override
	public String getCity(String keyword) {
		
		return null;
	}

	@Override
	public String getAirport(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
