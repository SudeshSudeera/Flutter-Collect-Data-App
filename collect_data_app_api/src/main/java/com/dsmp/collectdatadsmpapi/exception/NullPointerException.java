package com.dsmp.collectdatadsmpapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NullPointerException extends RuntimeException{
    public NullPointerException(String message) {
        super(message);
    }
}
