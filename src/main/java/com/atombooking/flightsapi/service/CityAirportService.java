package com.atombooking.flightsapi.service;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.response.locationapi.LocationApiDto;

@Service
public interface CityAirportService {

	LocationApiDto getCityAndAirport(String keyword);
	
}
