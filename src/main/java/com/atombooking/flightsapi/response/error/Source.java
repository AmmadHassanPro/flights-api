
package com.atombooking.flightsapi.response.error;

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
    "parameter",
    "example"
})
@Generated("jsonschema2pojo")
public class Source {

    @JsonProperty("parameter")
    private String parameter;
    @JsonProperty("example")
    private String example;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("parameter")
    public String getParameter() {
        return parameter;
    }

    @JsonProperty("parameter")
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @JsonProperty("example")
    public String getExample() {
        return example;
    }

    @JsonProperty("example")
    public void setExample(String example) {
        this.example = example;
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
