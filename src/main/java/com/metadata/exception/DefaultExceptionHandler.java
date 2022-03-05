package com.metadata.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RecordAlreadyExist.class, RecordNotExist.class , CourseExceedLimitException.class, StudentsExceedLimitException.class})
    ResponseEntity handleRecordAlreadyExist(Exception e) {
        return new ResponseEntity(new RestErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity handleRecordAlreadyExist(DataIntegrityViolationException e) {
        return new ResponseEntity(new RestErrorResponse("Rule Violation"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity handle(Exception e) {
        return new ResponseEntity(new RestErrorResponse("Ops, something wrong happens"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }




}