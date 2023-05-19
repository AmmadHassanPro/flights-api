package com.atombooking.flightsapi.service;

import com.atombooking.flightsapi.request.flightoffers.FlightOfferRequest;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;

public interface FlightOffersService {

	FlightOffersResponse getFlightOffers(FlightOfferRequest req) ;
}
