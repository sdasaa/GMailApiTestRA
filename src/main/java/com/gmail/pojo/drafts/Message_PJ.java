package com.gmail.pojo.drafts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message_PJ {
    @JsonProperty("id")
    private String id;
    @JsonProperty("threadId")
    private String threadId;
    @JsonProperty("labelIds")
    private List<String> labelIds;
    @JsonProperty("snippet")
    private String snippet;
    @JsonProperty("payload")
    private Payload_PJ payload;
    @JsonProperty("sizeEstimate")
    private Integer sizeEstimate;
    @JsonProperty("historyId")
    private String historyId;
    @JsonProperty("internalDate")
    private String internalDate;
    @JsonProperty("raw")
    private String raw;

    // Explicitly creating default constructor to facilitate requestPayload_pojo creation without builder() design pattern
    public Message_PJ(){
    }

    Message_PJ(String id, String threadId, List<String> labelIds, String snippet, Payload_PJ payload, Integer sizeEstimate, String historyId, String internalDate, String raw) {
        this.id = id;
        this.threadId = threadId;
        this.labelIds = labelIds;
        this.snippet = snippet;
        this.payload = payload;
        this.sizeEstimate = sizeEstimate;
        this.historyId = historyId;
        this.internalDate = internalDate;
        this.raw = raw;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {
        private String id;
        private String threadId;
        private List<String> labelIds;
        private String snippet;
        private Payload_PJ payload;
        private Integer sizeEstimate;
        private String historyId;
        private String internalDate;
        private String raw;

        MessageBuilder() {
        }

        @JsonProperty("id")
        public MessageBuilder id(String id) {
            this.id = id;
            return this;
        }

        @JsonProperty("threadId")
        public MessageBuilder threadId(String threadId) {
            this.threadId = threadId;
            return this;
        }

        @JsonProperty("labelIds")
        public MessageBuilder labelIds(List<String> labelIds) {
            this.labelIds = labelIds;
            return this;
        }

        @JsonProperty("snippet")
        public MessageBuilder snippet(String snippet) {
            this.snippet = snippet;
            return this;
        }

        @JsonProperty("payload")
        public MessageBuilder payload(Payload_PJ payload) {
            this.payload = payload;
            return this;
        }

        @JsonProperty("sizeEstimate")
        public MessageBuilder sizeEstimate(Integer sizeEstimate) {
            this.sizeEstimate = sizeEstimate;
            return this;
        }

        @JsonProperty("historyId")
        public MessageBuilder historyId(String historyId) {
            this.historyId = historyId;
            return this;
        }

        @JsonProperty("internalDate")
        public MessageBuilder internalDate(String internalDate) {
            this.internalDate = internalDate;
            return this;
        }

        @JsonProperty("raw")
        public MessageBuilder raw(String raw) {
            this.raw = raw;
            return this;
        }

        public Message_PJ build() {
            return new Message_PJ(this.id, this.threadId, this.labelIds, this.snippet, this.payload, this.sizeEstimate, this.historyId, this.internalDate, this.raw);
        }

        public String toString() {
            return "Message.MessageBuilder(id=" + this.id + ", threadId=" + this.threadId + ", labelIds=" + this.labelIds + ", snippet=" + this.snippet + ", payload=" + this.payload + ", sizeEstimate=" + this.sizeEstimate + ", historyId=" + this.historyId + ", internalDate=" + this.internalDate + ", raw=" + this.raw + ")";
        }
    }
}