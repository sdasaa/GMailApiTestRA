package com.gmail.pojo.drafts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Body_PJ {
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("data")
    private String data;
    @JsonProperty("attachmentId")
    private String attachmentId;
}