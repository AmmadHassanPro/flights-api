package com.atombooking.flightsapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atombooking.flightsapi.config.DatumMapper;
import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.Datum2;
import com.atombooking.flightsapi.response.locationapi.LocationAPIConvResp;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;
import com.atombooking.flightsapi.service.CityAirportService;

import reactor.core.publisher.Mono;

@Service
public class CityAirportServiceImpl implements CityAirportService {
	
	@Autowired
	WebClient client;
	@Value("${amadeus.base}")
	String base;
	@Value("${amadeus.airportAndCityEndpoint}")
	String endpointUrl;

	
	@Override
	public LocationAPIConvResp getCityAndAirport(String keyword) {
		Mono<LocationAPIResponse> resp = client.get().uri(base+endpointUrl+keyword).retrieve().bodyToMono(LocationAPIResponse.class);
		return convert(resp.block());
	}
	
	/*@param LocationAPIResponse
	 * 
	 * This method takes the response returned from the backend service and combines relavant airports and cities togather
	 * Earlier the service would return different types of json, ones with matching cities and the others with matching airports
	 * This method combines city and its corresponding airports togeather
	 */
	private LocationAPIConvResp convert(LocationAPIResponse resp) {
		
		LocationAPIConvResp resp2 = new LocationAPIConvResp();
		List<Datum2> newData = new ArrayList<Datum2>();
		resp2.setData(newData);
		//go over responses and group the airports by the city code
		
		Map<String, List<Datum>> map = new HashMap<String, List<Datum>>();
		
		if(resp.getData()!=null) {
			// grouping all the airports by city code
			resp.getData().stream().forEach(data -> {
				String cityCode = data.getAddress().getCityCode();
				if(data.getSubType().equals("AIRPORT")) {
					if(!map.containsKey(cityCode)) {
						map.put(cityCode, new ArrayList<Datum>());
					}
					
					map.get(cityCode).add(data);
				}
				
			});
			// combinig city and airports
			resp.getData().stream().forEach( data -> {
				if(data.getSubType().equals("CITY")) {
				String cityCode = data.getAddress().getCityCode();
				Datum2 newRespData = copyData(data);
				newRespData.setAirports(map.get(cityCode));
				newData.add(newRespData);
				}
			});
		}
		
		return resp2;
	}


	private Datum2 copyData(Datum data) {
		return DatumMapper.INSTANCE.convert(data);
	}
	
	
	
	
	

}
