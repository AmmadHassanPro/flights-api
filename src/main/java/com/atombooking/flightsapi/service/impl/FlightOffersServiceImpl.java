package com.atombooking.flightsapi.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.service.FlightOffersService;

import reactor.core.publisher.Mono;
@Service
public class FlightOffersServiceImpl implements FlightOffersService{

	private WebClient client;
	private String base;
	private String endpointUrl;
	
	public FlightOffersServiceImpl(WebClient client,@Value("${amadeus.base}") String base,@Value("${amadeus.flightOffersEndpoint}") String endpointUrl) {
		this.client = client;
		this.base= base;
		this.endpointUrl = endpointUrl;
	}
	
	@Override
	public FlightOffersResponse getFlightOffers(String Orig, String Dest, LocalDate Depart, LocalDate Return, Integer numberOfAdults) {
		
		Mono<FlightOffersResponse> resp = client.get().uri(
				uriBuilder ->
				uriBuilder.path(base + endpointUrl)
				.queryParam("originLocationCode", Orig)
				.queryParam("destinationLocationCode", Dest)
				.queryParam("departureDate", Depart)
				.queryParam("returnDate", Return)
				.queryParam("adults", numberOfAdults)
				.build())
				.retrieve()
				.bodyToMono(FlightOffersResponse.class);
		
		return resp.block();
	}

}
