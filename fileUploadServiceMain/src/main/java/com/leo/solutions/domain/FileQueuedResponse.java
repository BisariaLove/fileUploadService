package com.leo.solutions.domain;
/*
 * @author love.bisaria on 26/12/18
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileQueuedResponse {

    @JsonProperty("id")
    private String uploadId;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("timestamp")
    private String timestamp;


    public static class Builder{

        private String uploadId;
        private String fileName;
        private String timestamp;

        public Builder(String uploadId){
            this.uploadId = uploadId;
        }

        public Builder withFileName(String fileName){
            this.fileName = fileName;
            return this;
        }

        public Builder withTimestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public FileQueuedResponse build(){
            FileQueuedResponse obj = new FileQueuedResponse();
            obj.fileName = this.fileName;
            obj.uploadId = this.uploadId;
            obj.timestamp = this.timestamp;

            return obj;
        }

    }
}
