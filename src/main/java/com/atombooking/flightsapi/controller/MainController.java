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

import com.atombooking.flightsapi.config.EndpointUrls;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.service.CityAirportService;
import com.atombooking.flightsapi.service.FlightOffersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(EndpointUrls.MAIN_CONTROLLER_V1)
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	private static ObjectMapper mapper = new ObjectMapper();
	
	private CityAirportService cAService;
	private FlightOffersService fOService;
	
	public MainController(CityAirportService cA, FlightOffersService fO) {
		this.cAService= cA;
		this.fOService = fO;
	}
	
	@GetMapping(EndpointUrls.GET_CITY_AND_AIRPORT+"/{keyword}")
	public ResponseEntity<LocationApiDto> getCityAndAirports(@PathVariable String keyword, @RequestHeader(name="consumer-name") String consumerName
			,@RequestHeader(name="request-uuid") String requuestUUID) {
		String headersLog= "{Consumer Name: "+consumerName+" , Request UUID: "+requuestUUID+"}";
		logger.info("Endpoint url "+EndpointUrls.GET_CITY_AND_AIRPORT+"/"+keyword + headersLog);
		LocationApiDto resp = null;
		try {
		resp =  cAService.getCityAndAirport(keyword);
		}
		catch(Exception ex) {
			logger.info(headersLog + ", Exception: "+ ex.toString());
			logger.info("returning: "+ HttpStatus.INTERNAL_SERVER_ERROR + headersLog) ;
			return new ResponseEntity<>(new LocationApiDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if( resp.getData() == null || resp.getData().size() == 0) {
			logger.info("Empty Response" + headersLog);
			logger.info("returning: "+ HttpStatus.NOT_FOUND + headersLog);
			return new ResponseEntity<>(new LocationApiDto(), HttpStatus.NOT_FOUND);
		}
		logger.info("Returning : " + HttpStatus.OK +headersLog);
		if(logger.isDebugEnabled()) {
			try {
				logger.debug("Resposne Returned : " + mapper.writeValueAsString(resp) + headersLog);
			} catch (JsonProcessingException e) {
				logger.info("Exception while writing value as String: " + e + headersLog);
			}
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping(EndpointUrls.GET_OFFERS)
	public ResponseEntity<FlightOffersResponse> getFlightOffers(@RequestParam String originLocationCode , @RequestParam String destinationLocationCode , 
			@RequestParam String departureDate , @RequestParam Optional<String> returnDate, @RequestParam Integer adults, @RequestParam Boolean nonStop,
			@RequestHeader(name="consumer-name") String consumerName
			,@RequestHeader(name="request-uuid") String requuestUUID){
		String headersLog= "{Consumer Name: "+consumerName+" , Request UUID: "+requuestUUID+"}";
		logger.info("Endpoint Url "+EndpointUrls.GET_OFFERS + headersLog);
		logger.debug("originLocationCode:"+originLocationCode+", destinationLocationCode:"+destinationLocationCode+", "
				+ "departureDate:"+departureDate+", returnDate:"+ (returnDate.isPresent() ? returnDate.get() : "") + ", adults:"+adults+", nonStop:"+nonStop + headersLog);
		LocalDate dep = LocalDate.parse(departureDate);
		Optional<LocalDate> ret =  (returnDate.isEmpty()) ? Optional.empty() : Optional.of(LocalDate.parse(returnDate.get()));
		FlightOffersResponse resp = null;
		try {
		resp = fOService.getFlightOffers(originLocationCode, destinationLocationCode, dep, ret, adults, nonStop);
		}
		catch (Exception ex) {
			logger.info("Exception Occured on Endpoint " + EndpointUrls.GET_OFFERS +" , Exception:"+ex + headersLog);
			logger.info("Returning Status code, "+ HttpStatus.INTERNAL_SERVER_ERROR + headersLog);
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(resp== null || resp.getData()== null || resp.getData().size()==0) {
			return new ResponseEntity<>(new FlightOffersResponse(), HttpStatus.NOT_FOUND);
		}
		logger.info("Returning Response, status: "+ HttpStatus.OK + headersLog);
		if(logger.isDebugEnabled()) {
			try {
				logger.debug("Resposne Returned : " + mapper.writeValueAsString(resp) + headersLog);
			} catch (JsonProcessingException e) {
				logger.info("Exception while writing value as String: " + e+ headersLog);
			}
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}

}
