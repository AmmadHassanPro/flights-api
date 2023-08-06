package com.atombooking.flightsapi.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.atombooking.flightsapi.response.locationapi.Address;
import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;

public class LocationApiDtoConverterTest {

	@Test
	public void convertToDtoTest() {
		
		LocationAPIResponse testResp = getTestResp();
		LocationApiDto dto = LocationApiDtoConverter.getInstance().convertToDto(testResp);
		
		assertEquals(1 , dto.getData().size());
		assertEquals("CHICAGO", dto.getData().get(0).getName());
		assertEquals(1 , dto.getData().get(0).getAirports().size());
		assertEquals(dto.getData().get(0).getAirports().get(0).getAddress().getCityCode() , dto.getData().get(0).getAddress().getCityCode());
		
	}
	
	private LocationAPIResponse getTestResp() {
		LocationAPIResponse testResp = new LocationAPIResponse();
		
		
		Datum data1 = new Datum();
		data1.setName("CHICAGO");
		data1.setSubType("CITY");
		
		Address chiCityAddress = new Address();
		chiCityAddress.setCityName("CHICAGO");
		chiCityAddress.setCityCode("CHI");
		
		data1.setAddress(chiCityAddress);
		
		
		Datum data2 = new Datum();
		data2.setName("O HARE INTERNATIONAL");
		data2.setSubType("AIRPORT");
		
		Address chiAirportAddress = new Address();
		chiAirportAddress.setCityName("CHICAGO");
		chiAirportAddress.setCityCode("CHI");
		data2.setAddress(chiAirportAddress);
		
		List<Datum> list = new ArrayList<>();
		list.add(data1);
		list.add(data2);
		
		testResp.setData(list);
		
		return testResp;
	}
}
