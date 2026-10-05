package com.piyus.GlobalException.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDTO {

    private LocalDateTime timeStamp;
    private int statusCode;
    private String Error;
    private String message;
    private String path;
    Map<String, String>fieldErrors;

    public ValidationExceptionResponseDTO(
            LocalDateTime timeStamp,
            int statusCode,
            String error,
            String message,
            String path,
            Map<String, String> fieldErrors
    ) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        Error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
