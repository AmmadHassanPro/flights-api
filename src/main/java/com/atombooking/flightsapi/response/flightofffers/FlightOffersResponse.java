
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
    "meta",
    "data",
    "dictionaries"
})
@Generated("jsonschema2pojo")
public class FlightOffersResponse {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private List<Data> data;
    @JsonProperty("dictionaries")
    private Dictionaries dictionaries;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("data")
    public List<Data> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Data> data) {
        this.data = data;
    }

    @JsonProperty("dictionaries")
    public Dictionaries getDictionaries() {
        return dictionaries;
    }

    @JsonProperty("dictionaries")
    public void setDictionaries(Dictionaries dictionaries) {
        this.dictionaries = dictionaries;
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
