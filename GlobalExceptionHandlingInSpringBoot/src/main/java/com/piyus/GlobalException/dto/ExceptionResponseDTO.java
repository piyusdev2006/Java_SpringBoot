package com.piyus.GlobalException.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDTO {

    private LocalDateTime timeStamp;
    private int statusCode;
    private String Error;
    private String message;
    private String path;

    public ExceptionResponseDTO(LocalDateTime timeStamp, int statusCode, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        Error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
