package com.atombooking.flightsapi.service;

import org.springframework.stereotype.Service;

@Service
public interface CityAirportService {

	String getCityAndAirport(String keyword);
	
}
