
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
    "segmentId",
    "cabin",
    "fareBasis",
    "class",
    "includedCheckedBags"
})
@Generated("jsonschema2pojo")
public class FareDetailsBySegment {

    @JsonProperty("segmentId")
    private String segmentId;
    @JsonProperty("cabin")
    private String cabin;
    @JsonProperty("fareBasis")
    private String fareBasis;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("includedCheckedBags")
    private IncludedCheckedBags includedCheckedBags;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("segmentId")
    public String getSegmentId() {
        return segmentId;
    }

    @JsonProperty("segmentId")
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    @JsonProperty("cabin")
    public String getCabin() {
        return cabin;
    }

    @JsonProperty("cabin")
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    @JsonProperty("fareBasis")
    public String getFareBasis() {
        return fareBasis;
    }

    @JsonProperty("fareBasis")
    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("includedCheckedBags")
    public IncludedCheckedBags getIncludedCheckedBags() {
        return includedCheckedBags;
    }

    @JsonProperty("includedCheckedBags")
    public void setIncludedCheckedBags(IncludedCheckedBags includedCheckedBags) {
        this.includedCheckedBags = includedCheckedBags;
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
