package com.gmail.pojo.drafts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload_PJ {
    @JsonProperty("partId")
    private String partId;
    @JsonProperty("mimeType")
    private String mimeType;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("headers")
    private List<Headers_PJ> headers;
    @JsonProperty("body")
    private Body_PJ body;
    @JsonProperty("parts")
    private List<Parts_PJ> parts;
}