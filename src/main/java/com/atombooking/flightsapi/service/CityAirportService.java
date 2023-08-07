package com.atombooking.flightsapi.service;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.response.locationapi.LocationApiAggregatedResponse;

@Service
public interface CityAirportService {

	LocationApiAggregatedResponse getCityAndAirport(String keyword);
	
}
