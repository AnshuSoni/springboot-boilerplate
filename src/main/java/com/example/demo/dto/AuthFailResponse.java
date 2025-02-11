package com.example.demo.dto;


import org.springframework.http.HttpStatus;

public class AuthFailResponse {

    private String message;
    private HttpStatus status;

    public AuthFailResponse(String message) {
        status = HttpStatus.FORBIDDEN;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
