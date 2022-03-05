package com.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gfaraujo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotExist extends RuntimeException{

    public RecordNotExist(String message) {
        super(message);
    }
}
