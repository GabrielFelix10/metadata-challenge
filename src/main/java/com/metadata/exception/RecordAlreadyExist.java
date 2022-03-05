package com.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gfaraujo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordAlreadyExist  extends RuntimeException{

    public RecordAlreadyExist(String message) {
        super(message);
    }
}
