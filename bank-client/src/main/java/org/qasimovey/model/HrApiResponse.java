package org.qasimovey.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class HrApiResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private  String message;
    @JsonProperty("errors")
    private  List<String> errors;
}
