package com.atombooking.flightsapi.converters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atombooking.flightsapi.config.DatumMapper;
import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.Datum2;
import com.atombooking.flightsapi.response.locationapi.LocationApiAggregatedResponse;
import com.atombooking.flightsapi.response.locationapi.LocationAPIResponse;

/*LocationApiDtoConverter aids in converting the response recieved from backend service LocationAPIResponse to LocationApiDto
 * It combines the cities and airports together based on common citycode, so its easier for consumers to see the city and airport together 
 */
public class LocationApiDtoConverter {
	
	private static LocationApiDtoConverter instance;
	
	public static LocationApiDtoConverter getInstance() {
		if(instance == null) {
			instance = new LocationApiDtoConverter();
		}
		return instance;
	}
	
	private LocationApiDtoConverter() {
		
	}

	/*@param resp This parameter is the response returned from the back end service
	 * @return LocationApiDto This returns the dto object, which groups the relevant cities and airports together
	 * This method takes the response returned from the back end service and combines relevant airports and cities together
	 * for easier readability by the consumers
	 */
	public LocationApiAggregatedResponse convertToDto(LocationAPIResponse resp) {
		
		LocationApiAggregatedResponse resp2 = new LocationApiAggregatedResponse();
		List<Datum2> newData = new ArrayList<>();
		resp2.setData(newData);
		//go over responses and group the airports by the city code
		
		Map<String, List<Datum>> map = new HashMap<>();
		
		if(resp.getData()!=null) {
			// grouping all the airports by city code
			resp.getData().stream().forEach(data -> {
				String cityCode = data.getAddress().getCityCode();
				if(data.getSubType().equals("AIRPORT")) {
					if(!map.containsKey(cityCode)) {
						map.put(cityCode, new ArrayList<>());
					}
					
					map.get(cityCode).add(data);
				}
				
			});
			// combining city and airports with matching city code
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

	private Datum2 copyData(Datum data)  {
		return DatumMapper.INSTANCE.convert(data);
	}
}
