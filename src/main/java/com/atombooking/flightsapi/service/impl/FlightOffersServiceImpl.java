package com.atombooking.flightsapi.service.impl;

import java.time.LocalDate;
import java.util.Optional;

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
	@Value("${amadeus.maxNumberOfFlights}")
	private int maxFlights = 1;
	
	public FlightOffersServiceImpl(WebClient client,@Value("${amadeus.base}") String base,@Value("${amadeus.flightOffersEndpoint}") String endpointUrl) {
		this.client = client;
		this.base= base;
		this.endpointUrl = endpointUrl;
	}
	
	@Override
	public FlightOffersResponse getFlightOffers(String Orig, String Dest, LocalDate Depart, Optional<LocalDate> Return, Integer numberOfAdults, boolean nonStop) {
		
		Mono<FlightOffersResponse> resp = client.get().uri(base+ endpointUrl, 
				uriBuilder ->
			{
				uriBuilder
				.queryParam("originLocationCode", Orig)
				.queryParam("destinationLocationCode", Dest)
				.queryParam("departureDate", Depart)
				.queryParam("adults", numberOfAdults)
				.queryParam("max", maxFlights) // setting max number of flight records to return
				.queryParam("currencyCode", "USD");
				if(Return.isPresent()) {
					uriBuilder.queryParam("returnDate" , Return.get());
				}
				
				if(nonStop) {
					uriBuilder.queryParam("nonStop" , nonStop);
				}
				
				return uriBuilder.build();
			}
				)
				.retrieve()
				.bodyToMono(FlightOffersResponse.class);
		
		return resp.block();
	}

}
