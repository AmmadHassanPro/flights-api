package com.atombooking.flightsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.service.CityAirportService;

@RestController
@RequestMapping("/v1/flights-api/")
public class MainController {
	
	@Autowired
	CityAirportService service;
	
	@GetMapping("getCityAndAirport/{keyword}")
	public LocationApiDto getCityAndAirports(@PathVariable String keyword) {
		return service.getCityAndAirport(keyword);
	}
}
