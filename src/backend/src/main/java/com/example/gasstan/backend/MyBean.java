package com.example.gasstan.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private boolean success;
    private String message;

    public boolean getSuccess() {
        return success;
    }

    public MyBean setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MyBean setMessage(String message) {
        this.message = message;
        return this;
    }
}