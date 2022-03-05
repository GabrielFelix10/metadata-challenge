package com.metadata.exception;

public class StudentsExceedLimitException extends RuntimeException{

    public StudentsExceedLimitException(String message) {
        super(message);
    }
}
