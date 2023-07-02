package com.atombooking.flightsapi.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.atombooking.flightsapi.converters.LocationApiDtoConverter;
import com.atombooking.flightsapi.response.locationapi.Address;
import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.service.CityAirportService;


@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)

public class MainControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CityAirportService service;
	
	
	@Test
	public void shouldReturnValidRespwithValidIn() throws Exception {
		
		when(service.getCityAndAirport(eq("Chicago"))).thenReturn(getTestResp());
		
		this.mockMvc.perform(get("/v1/flights-api/get-city-and-airport/Chicago")).andExpect(status().isOk())
		.andExpect(content().string(containsString("O HARE INTERNATIONAL")));
		
	}
	
	@Test
	public void shouldReturn404() throws Exception {
		when(service.getCityAndAirport(eq("nonExistentCity"))).thenReturn(new LocationApiDto());
		
		this.mockMvc.perform(get("/v1/flights-api/get-city-and-airport/nonExistentCity")).andExpect(status().isNotFound());
		
	}
	
	private LocationApiDto getTestResp() {
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
		
		return LocationApiDtoConverter.getInstance().convertToDto(testResp);
	}

}
