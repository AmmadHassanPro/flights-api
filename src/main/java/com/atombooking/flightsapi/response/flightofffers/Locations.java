
package com.atombooking.flightsapi.response.flightofffers;

import java.util.LinkedHashMap;
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
    "EWR",
    "MAD",
    "LHR",
    "JFK"
})
@Generated("jsonschema2pojo")
public class Locations {

    @JsonProperty("EWR")
    private Ewr ewr;
    @JsonProperty("MAD")
    private Mad mad;
    @JsonProperty("LHR")
    private Lhr lhr;
    @JsonProperty("JFK")
    private Jfk jfk;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("EWR")
    public Ewr getEwr() {
        return ewr;
    }

    @JsonProperty("EWR")
    public void setEwr(Ewr ewr) {
        this.ewr = ewr;
    }

    @JsonProperty("MAD")
    public Mad getMad() {
        return mad;
    }

    @JsonProperty("MAD")
    public void setMad(Mad mad) {
        this.mad = mad;
    }

    @JsonProperty("LHR")
    public Lhr getLhr() {
        return lhr;
    }

    @JsonProperty("LHR")
    public void setLhr(Lhr lhr) {
        this.lhr = lhr;
    }

    @JsonProperty("JFK")
    public Jfk getJfk() {
        return jfk;
    }

    @JsonProperty("JFK")
    public void setJfk(Jfk jfk) {
        this.jfk = jfk;
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
