package com.atombooking.flightsapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.converters.LocationApiDtoConverter;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
import com.atombooking.flightsapi.service.CityAirportService;

import reactor.core.publisher.Mono;

@Service
public class CityAirportServiceImpl implements CityAirportService {
	
	private WebClient client;
	private String base;
	private String endpointUrl;
	
	public CityAirportServiceImpl(WebClient client,@Value("${amadeus.base}") String base,@Value("${amadeus.airportAndCityEndpoint}") String endpointUrl) {
		this.client = client;
		this.base= base;
		this.endpointUrl = endpointUrl;
	}
	
	@Override
	public LocationApiDto getCityAndAirport(String keyword) {
		Mono<LocationAPIResponse> resp = client.get().uri(base+endpointUrl+keyword).retrieve().bodyToMono(LocationAPIResponse.class);
		return LocationApiDtoConverter.getInstance().convertToDto(resp.block());
	}


}
