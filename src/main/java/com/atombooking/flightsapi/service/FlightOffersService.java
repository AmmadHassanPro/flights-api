package com.atombooking.flightsapi.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
@Service
public interface FlightOffersService {

	FlightOffersResponse getFlightOffers(String Orig, String Dest , LocalDate Depart, LocalDate Return, Integer numberOfAdults) ;
}
