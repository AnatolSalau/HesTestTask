package com.example.hestesttask.exception_handler;

import com.example.hestesttask.dto.ServiceErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ServiceErrorDto> handleUserRuntimeException(Exception ex) {
        return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new ServiceErrorDto(HttpStatus.UNAUTHORIZED.value() ,
                          ex.getMessage()
                    )
              );
    }
}