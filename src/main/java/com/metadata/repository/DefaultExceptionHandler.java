package com.metadata.repository;


import com.metadata.exception.RecordAlreadyExist;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class DefaultExceptionHandler {

    @ExceptionHandler(RecordAlreadyExist.class)
    ResponseEntity handleRecordAlreadyExist(Exception e) {
        return new ResponseEntity("Already exist a register with this values", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity handle(Exception e) {
        return new ResponseEntity("Operation Not allowed", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }




}