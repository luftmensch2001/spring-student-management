package com.student.server.models;

public class ResponseObject {
    String status;
    String message;
    Object object;

    public ResponseObject(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
