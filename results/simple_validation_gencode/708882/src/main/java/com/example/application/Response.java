package com.example.application;

/**
 * Standard response object for API operations.
 * Contains success status, message, and optional data.
 */
public class Response {
    private boolean success;
    private String message;
    private Object data;

    /**
     * Default constructor.
     */
    public Response() {
        this.success = false;
        this.message = "";
    }

    /**
     * Constructor with all fields.
     * @param success whether the operation succeeded
     * @param message the response message
     * @param data the response data
     */
    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}