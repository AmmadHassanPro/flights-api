
package com.atombooking.flightsapi.response.flightofffers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "source",
    "instantTicketingRequired",
    "nonHomogeneous",
    "oneWay",
    "lastTicketingDate",
    "numberOfBookableSeats",
    "itineraries",
    "price",
    "pricingOptions",
    "validatingAirlineCodes",
    "travelerPricings"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("source")
    private String source;
    @JsonProperty("instantTicketingRequired")
    private Boolean instantTicketingRequired;
    @JsonProperty("nonHomogeneous")
    private Boolean nonHomogeneous;
    @JsonProperty("oneWay")
    private Boolean oneWay;
    @JsonProperty("lastTicketingDate")
    private String lastTicketingDate;
    @JsonProperty("numberOfBookableSeats")
    private Integer numberOfBookableSeats;
    @JsonProperty("itineraries")
    private List<Itinerary> itineraries;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("pricingOptions")
    private PricingOptions pricingOptions;
    @JsonProperty("validatingAirlineCodes")
    private List<String> validatingAirlineCodes;
    @JsonProperty("travelerPricings")
    private List<TravelerPricing> travelerPricings;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("instantTicketingRequired")
    public Boolean getInstantTicketingRequired() {
        return instantTicketingRequired;
    }

    @JsonProperty("instantTicketingRequired")
    public void setInstantTicketingRequired(Boolean instantTicketingRequired) {
        this.instantTicketingRequired = instantTicketingRequired;
    }

    @JsonProperty("nonHomogeneous")
    public Boolean getNonHomogeneous() {
        return nonHomogeneous;
    }

    @JsonProperty("nonHomogeneous")
    public void setNonHomogeneous(Boolean nonHomogeneous) {
        this.nonHomogeneous = nonHomogeneous;
    }

    @JsonProperty("oneWay")
    public Boolean getOneWay() {
        return oneWay;
    }

    @JsonProperty("oneWay")
    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }

    @JsonProperty("lastTicketingDate")
    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    @JsonProperty("lastTicketingDate")
    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    @JsonProperty("numberOfBookableSeats")
    public Integer getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    @JsonProperty("numberOfBookableSeats")
    public void setNumberOfBookableSeats(Integer numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    @JsonProperty("itineraries")
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    @JsonProperty("itineraries")
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("pricingOptions")
    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    @JsonProperty("pricingOptions")
    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    @JsonProperty("validatingAirlineCodes")
    public List<String> getValidatingAirlineCodes() {
        return validatingAirlineCodes;
    }

    @JsonProperty("validatingAirlineCodes")
    public void setValidatingAirlineCodes(List<String> validatingAirlineCodes) {
        this.validatingAirlineCodes = validatingAirlineCodes;
    }

    @JsonProperty("travelerPricings")
    public List<TravelerPricing> getTravelerPricings() {
        return travelerPricings;
    }

    @JsonProperty("travelerPricings")
    public void setTravelerPricings(List<TravelerPricing> travelerPricings) {
        this.travelerPricings = travelerPricings;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
