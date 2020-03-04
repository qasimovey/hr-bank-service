package org.qasimovey.exceptions;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@ToString
@Data
public class ErrorInfo {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorInfo(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorInfo(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

}
