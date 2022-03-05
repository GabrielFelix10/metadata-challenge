package com.metadata.repository;


import com.metadata.exception.CustomError;
import com.metadata.exception.RecordAlreadyExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordAlreadyExist.class)
    public ResponseEntity handle(RecordAlreadyExist e, WebRequest request) {
        return ResponseEntity.status(400).body(new CustomError(400, e.getMessage(), "Already exist a register with this values"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(Exception e, WebRequest request) {
        return ResponseEntity.status(400).body(new CustomError(400, e.getMessage(), "This operation is not allowed"));
    }




}