package com.atombooking.flightsapi.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atombooking.flightsapi.config.ConfigConstants;
import com.atombooking.flightsapi.config.EndpointUrls;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiAggregatedResponse;
import com.atombooking.flightsapi.service.CityAirportService;
import com.atombooking.flightsapi.service.FlightOffersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
@Tag(name = "Main Controller", description = "The controller that contains all the endpoints")

@RestController
@RequestMapping(EndpointUrls.MAIN_CONTROLLER_V1)
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private CityAirportService cAService;
	private FlightOffersService fOService;
	
	public MainController(CityAirportService cA, FlightOffersService fO) {
		this.cAService= cA;
		this.fOService = fO;
	}
	 @Operation(
		      summary = "Retrieve city and associated airport information by providing keyword",
		      description = "Find city and associated airports by providing keyword. The keyword can match a city name or an airport. "
		      + "This endpoint supports searching city or airport by providing the keyword, It Response would contain the cities with associated airports",
		      tags = { "Search Cities", "Search Airports" })
	 @ApiResponses({
	      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LocationApiAggregatedResponse.class), mediaType = "application/json")})
	 })
	@GetMapping(EndpointUrls.GET_CITY_AND_AIRPORT+"/{keyword}")
	public ResponseEntity<LocationApiAggregatedResponse> getCityAndAirports(@PathVariable String keyword, @RequestHeader(name=ConfigConstants.HEADER_CONSUMER_NAME) String consumerName
			,@RequestHeader(name=ConfigConstants.HEADER_REQUEST_UUID) String requuestUUID) {
		LocationApiAggregatedResponse resp = null;
		try {
		resp =  cAService.getCityAndAirport(keyword);
		}
		catch(Exception ex) {
			logger.info(ex.toString());
			return new ResponseEntity<>(new LocationApiAggregatedResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if( resp.getData() == null || resp.getData().size() == 0) {
			return new ResponseEntity<>(new LocationApiAggregatedResponse(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	 @Operation(
		      summary = "Retrieve Flight Offers from multiple airlines",
		      description = "Find the flight offers from multiple airlines all over the world.",
		      tags = { "Search Flight Offers" })
	 @ApiResponses({
	      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FlightOffersResponse.class), mediaType = "application/json")})
	 })
	@GetMapping(EndpointUrls.GET_OFFERS)
	public ResponseEntity<FlightOffersResponse> getFlightOffers(@RequestParam String originLocationCode , @RequestParam String destinationLocationCode , 
			@RequestParam String departureDate , @RequestParam Optional<String> returnDate, @RequestParam Integer adults, @RequestParam Boolean nonStop,
			@RequestHeader(name=ConfigConstants.HEADER_CONSUMER_NAME) String consumerName
			,@RequestHeader(name=ConfigConstants.HEADER_REQUEST_UUID) String requuestUUID){
		LocalDate dep = LocalDate.parse(departureDate);
		Optional<LocalDate> ret =  (returnDate.isEmpty()) ? Optional.empty() : Optional.of(LocalDate.parse(returnDate.get()));
		FlightOffersResponse resp = null;
		try {
		resp = fOService.getFlightOffers(originLocationCode, destinationLocationCode, dep, ret, adults, nonStop);
		}
		catch (Exception ex) {
			logger.info(ex.toString());
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(resp== null || resp.getData()== null || resp.getData().size()==0) {
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}

}
