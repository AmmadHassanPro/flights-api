package com.atombooking.flightsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<LocationApiDto> getCityAndAirports(@PathVariable String keyword) {
		LocationApiDto resp =  service.getCityAndAirport(keyword);
		if(resp.getData().size() == 0) {
			return new ResponseEntity<>(new LocationApiDto(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
