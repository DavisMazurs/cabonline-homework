package com.cabonline.homework.exception;

import lombok.Getter;

@Getter
public class RestErrorException extends RuntimeException {
    private final int code;

    public RestErrorException(String message, int code) {
        super(message);
        this.code = code;
    }
}
