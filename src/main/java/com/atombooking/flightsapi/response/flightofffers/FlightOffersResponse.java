
package com.atombooking.flightsapi.response.flightofffers;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "dictionaries"
})
@Generated("jsonschema2pojo")
public class FlightOffersResponse {
	
    @JsonProperty("data")
    private List<Data> data;
    @JsonProperty("dictionaries")
    private Dictionaries dictionaries;

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

}
