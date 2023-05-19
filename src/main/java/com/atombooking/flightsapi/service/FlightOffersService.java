package com.atombooking.flightsapi.service;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.request.flightoffers.FlightOfferRequest;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
@Service
public interface FlightOffersService {

	FlightOffersResponse getFlightOffers(FlightOfferRequest req) ;
}
