
package com.gmail.pojo.drafts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.pojo.drafts.Body_PJ;
import com.gmail.pojo.drafts.Headers_PJ;
import com.gmail.pojo.drafts.Parts_PJ;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parts_PJ {
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