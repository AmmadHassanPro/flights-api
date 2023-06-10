package com.atombooking.flightsapi.response.locationapi;

import java.util.List;

public class Datum2 extends Datum{

    private List<Datum> airports;

	public List<Datum> getAirports() {
		return airports;
	}

	public void setAirports(List<Datum> airport) {
		this.airports = airport;
	}

}
