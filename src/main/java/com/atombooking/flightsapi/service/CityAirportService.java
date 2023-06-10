package com.atombooking.flightsapi.service;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.response.locationapi.LocationAPIConvResp;

@Service
public interface CityAirportService {

	LocationAPIConvResp getCityAndAirport(String keyword);
	
}
