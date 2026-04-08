package com.gmail.pojo.drafts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.databind.annotation.JsonDeserialize(builder = DraftRoot_PJ.DraftRootBuilder.class)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DraftRoot_PJ {
    @JsonProperty("id")
    private String id;
    @JsonProperty("message")
    private Message_PJ message;
    @JsonProperty("drafts")
    private List<Draft_PJ> drafts = new ArrayList<Draft_PJ>();
    @JsonProperty("resultSizeEstimate")
    private Integer resultSizeEstimate;

    // Explicitly creating default constructor to facilitate requestPayload_pojo creation without builder() design pattern
    public DraftRoot_PJ(){

    }

    DraftRoot_PJ(String id, Message_PJ message, List<Draft_PJ> drafts, Integer resultSizeEstimate) {
        this.id = id;
        this.message = message;
        this.drafts = drafts;
        this.resultSizeEstimate = resultSizeEstimate;
    }

    public static DraftRootBuilder builder() {
        return new DraftRootBuilder();
    }

    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class DraftRootBuilder {
        private String id;
        private Message_PJ message;
        private List<Draft_PJ> drafts;
        private Integer resultSizeEstimate;

        DraftRootBuilder() {
        }

        @JsonProperty("id")
        public DraftRootBuilder id(String id) {
            this.id = id;
            return this;
        }

        @JsonProperty("message")
        public DraftRootBuilder message(Message_PJ message) {
            this.message = message;
            return this;
        }

        @JsonProperty("drafts")
        public DraftRootBuilder drafts(List<Draft_PJ> drafts) {
            this.drafts = drafts;
            return this;
        }

        @JsonProperty("resultSizeEstimate")
        public DraftRootBuilder resultSizeEstimate(Integer resultSizeEstimate) {
            this.resultSizeEstimate = resultSizeEstimate;
            return this;
        }

        public DraftRoot_PJ build() {
            return new DraftRoot_PJ(this.id, this.message, this.drafts, this.resultSizeEstimate);
        }

        public String toString() {
            return "DraftRoot_PJ.DraftRootBuilder(id=" + this.id + ", message=" + this.message + ", drafts=" + this.drafts + ", resultSizeEstimate=" + this.resultSizeEstimate + ")";
        }
    }
}