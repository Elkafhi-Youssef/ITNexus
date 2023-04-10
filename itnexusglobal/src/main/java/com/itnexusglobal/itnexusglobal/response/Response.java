package com.itnexusglobal.itnexusglobal.response;

public class Response {
    private String message;
    private Integer status;

    public Response(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message) {
        this.message = message;

    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}