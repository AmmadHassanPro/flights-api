
package com.atombooking.flightsapi.response.locationapi;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cityName",
    "cityCode",
    "countryName",
    "countryCode",
    "stateCode",
    "regionCode"
})
@Generated("jsonschema2pojo")
public class Address {

    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("stateCode")
    private String stateCode;
    @JsonProperty("regionCode")
    private String regionCode;

    @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }

    @JsonProperty("cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonProperty("cityCode")
    public String getCityCode() {
        return cityCode;
    }

    @JsonProperty("cityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("stateCode")
    public String getStateCode() {
        return stateCode;
    }

    @JsonProperty("stateCode")
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @JsonProperty("regionCode")
    public String getRegionCode() {
        return regionCode;
    }

    @JsonProperty("regionCode")
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

}
