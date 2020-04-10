package com.hello.store.test.web;

public class ResponseBaseData<T> implements Data {
    private boolean status = true;
    private int statusCode = 200;
    private String message;
    private T data;

    public ResponseBaseData() {
    }

    public ResponseBaseData(boolean status, int statusCode, String message, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
