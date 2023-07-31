package com.atombooking.flightsapi.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atombooking.flightsapi.config.EndpointUrls;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.service.CityAirportService;
import com.atombooking.flightsapi.service.FlightOffersService;

@RestController
@RequestMapping(EndpointUrls.MAIN_CONTROLLER_V1)
public class MainController {
	
	private CityAirportService cAService;
	private FlightOffersService fOService;
	
	public MainController(CityAirportService cA, FlightOffersService fO) {
		this.cAService= cA;
		this.fOService = fO;
	}
	
	@GetMapping(EndpointUrls.GET_CITY_AND_AIRPORT+"/{keyword}")
	public ResponseEntity<LocationApiDto> getCityAndAirports(@PathVariable String keyword) {
		LocationApiDto resp = null;
		try {
		resp =  cAService.getCityAndAirport(keyword);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(new LocationApiDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if( resp.getData() == null || resp.getData().size() == 0) {
			return new ResponseEntity<>(new LocationApiDto(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping(EndpointUrls.GET_OFFERS)
	public ResponseEntity<FlightOffersResponse> getFlightOffers(@RequestParam String originLocationCode , @RequestParam String destinationLocationCode , 
			@RequestParam String departureDate , @RequestParam Optional<String> returnDate, @RequestParam Integer adults, @RequestParam Boolean nonStop){
		
		LocalDate dep = LocalDate.parse(departureDate);
		Optional<LocalDate> ret =  (returnDate.isEmpty()) ? Optional.empty() : Optional.of(LocalDate.parse(returnDate.get()));
		FlightOffersResponse resp = null;
		try {
		resp = fOService.getFlightOffers(originLocationCode, destinationLocationCode, dep, ret, adults, nonStop);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(resp== null || resp.getData()== null || resp.getData().size()==0) {
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}
}
