package com.cabonline.homework.api;

import com.cabonline.homework.exception.RestErrorException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RestErrorException.class)
    public ResponseEntity<ErrorResponse> restErrorResponse(RestErrorException ex) {
        log.info("Rest error: {}", ex.getMessage());
        return ResponseEntity.status(ex.getCode())
                .body(new ErrorResponse()
                        .setMessage(ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        var error = String.format("Method %s is not supported", ex.getMethod());
        log.info(error);
        return ResponseEntity.status(405)
                .body(new ErrorResponse()
                        .setMessage(error));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleInternalError(Throwable t, HttpServletRequest request) {
        log.error("Internal error at path : {}", request.getServletPath(), t);
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse()
                        .setMessage("Internal server error!"));
    }


}
