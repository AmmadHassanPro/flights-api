
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
    "320",
    "744",
    "777"
})
@Generated("jsonschema2pojo")
public class Aircraft__1 {

    @JsonProperty("320")
    private String _320;
    @JsonProperty("744")
    private String _744;
    @JsonProperty("777")
    private String _777;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("320")
    public String get320() {
        return _320;
    }

    @JsonProperty("320")
    public void set320(String _320) {
        this._320 = _320;
    }

    @JsonProperty("744")
    public String get744() {
        return _744;
    }

    @JsonProperty("744")
    public void set744(String _744) {
        this._744 = _744;
    }

    @JsonProperty("777")
    public String get777() {
        return _777;
    }

    @JsonProperty("777")
    public void set777(String _777) {
        this._777 = _777;
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
