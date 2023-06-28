package com.atombooking.flightsapi.response.locationapi;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "subType",
    "name",
    "detailedName",
    "id",
    "iataCode",
    "geoCode",
    "address"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("type")
    private String type;
    @JsonProperty("subType")
    private String subType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("detailedName")
    private String detailedName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("iataCode")
    private String iataCode;
    @JsonProperty("address")
    private Address address;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("subType")
    public String getSubType() {
        return subType;
    }

    @JsonProperty("subType")
    public void setSubType(String subType) {
        this.subType = subType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("detailedName")
    public String getDetailedName() {
        return detailedName;
    }

    @JsonProperty("detailedName")
    public void setDetailedName(String detailedName) {
        this.detailedName = detailedName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("iataCode")
    public String getIataCode() {
        return iataCode;
    }

    @JsonProperty("iataCode")
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

}
