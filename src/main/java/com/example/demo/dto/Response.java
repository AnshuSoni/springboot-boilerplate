package com.example.demo.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response<T> {
    private Integer statusCode;
    private String message;
    private T body;

    public Response() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
