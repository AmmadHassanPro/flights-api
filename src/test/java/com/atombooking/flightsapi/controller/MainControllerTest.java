package com.atombooking.flightsapi.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import com.atombooking.flightsapi.config.ConfigConstants;
import com.atombooking.flightsapi.converters.LocationApiDtoConverter;
import com.atombooking.flightsapi.response.flightofffers.Data;
import com.atombooking.flightsapi.response.flightofffers.FlightOffersResponse;
import com.atombooking.flightsapi.response.locationapi.Address;
import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
import com.atombooking.flightsapi.response.locationapi.LocationApiDto;
import com.atombooking.flightsapi.service.CityAirportService;
import com.atombooking.flightsapi.service.FlightOffersService;


@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)

public class MainControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CityAirportService cAService;
	
	@MockBean
	private FlightOffersService fOService;
	
	
	@Test
	public void testgetCityAndAirports() throws Exception {
		
		when(cAService.getCityAndAirport(eq("Chicago"))).thenReturn(getTestResp());
		
		this.mockMvc.perform(get("/v1/flights-api/get-city-and-airport/Chicago").header(ConfigConstants.HEADER_CONSUMER_NAME, "JUNIT").header(ConfigConstants.HEADER_REQUEST_UUID, "abc-123-456")).andExpect(status().isOk())
		.andExpect(content().string(containsString("O HARE INTERNATIONAL")));
		
	}
	
	@Test
	public void shouldReturn404ForCityAndAirports() throws Exception {
		when(cAService.getCityAndAirport(eq("nonExistentCity"))).thenReturn(new LocationApiDto());
		
		this.mockMvc.perform(get("/v1/flights-api/get-city-and-airport/nonExistentCity").header(ConfigConstants.HEADER_CONSUMER_NAME, "JUNIT").header(ConfigConstants.HEADER_REQUEST_UUID, "abc-123-456")).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testgetFlightOffers() throws Exception {
		
		LocalDate dep = LocalDate.now().plusDays(10);
		LocalDate ret = LocalDate.now().plusDays(20); 
		String source = "SYD";
		String dest = "BKK";
		int numOfAdults = 5;
		boolean nonStop = true;
		
		MultiValueMap<String,String> queryParams = new HttpHeaders();
		queryParams.add("originLocationCode" , source);
		queryParams.add("destinationLocationCode" , dest);
		queryParams.add("departureDate" , dep.toString());
		queryParams.add("returnDate" , ret.toString());
		queryParams.add("adults" , String.valueOf(numOfAdults));
		queryParams.add("nonStop" , String.valueOf(nonStop));
		
		when(fOService.getFlightOffers(source, dest, dep, Optional.of(ret), numOfAdults, nonStop)).thenReturn(getFlightOfferTestResp());
		
		this.mockMvc.perform(get("/v1/flights-api/get-offers").header(ConfigConstants.HEADER_CONSUMER_NAME, "JUNIT").header(ConfigConstants.HEADER_REQUEST_UUID, "abc-123-456").queryParams(queryParams)).andExpect(status().isOk());
		
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
	
	// generates a dummy response with no actual values
	private FlightOffersResponse getFlightOfferTestResp() {
		
		FlightOffersResponse rep = new FlightOffersResponse();
		List<Data> dataList = new ArrayList<>();
		dataList.add(new Data());
		rep.setData(dataList);
		
		return rep;
	}
}
