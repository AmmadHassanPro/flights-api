package com.atombooking.flightsapi.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
@Service
public interface FlightOffersService {

	FlightOffersResponse getFlightOffers(String Orig, String Dest , Date Depart, Date Return, Integer numberOfAdults) ;
}
