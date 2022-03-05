package com.metadata.exception;

public final class RestErrorResponse {
    private final String message;

    public RestErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}