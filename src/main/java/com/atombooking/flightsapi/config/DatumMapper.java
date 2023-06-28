package com.atombooking.flightsapi.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.atombooking.flightsapi.response.locationapi.Datum;
import com.atombooking.flightsapi.response.locationapi.Datum2;

@Mapper
public interface DatumMapper {

	DatumMapper INSTANCE = Mappers.getMapper( DatumMapper.class );
	
    @Mapping(source = "type", target = "type")
    @Mapping(source = "subType", target = "subType")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "detailedName", target = "detailedName")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "iataCode", target = "iataCode")
    @Mapping(source = "address", target = "address")
   // @Mapping(source = "additionalProperties", target = "additionalProperties")
	Datum2 convert(Datum data);
}
