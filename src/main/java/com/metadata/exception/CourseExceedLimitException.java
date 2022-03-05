package com.metadata.exception;

public class CourseExceedLimitException extends RuntimeException{

    public CourseExceedLimitException(String message) {
        super(message);
    }
}
