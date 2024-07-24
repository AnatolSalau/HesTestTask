package com.example.hestesttask.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ServiceRuntimeException extends RuntimeException {
      @Serial
      private static final long serialVersionUID = -8167379135730748793L;

      private final int statusCode;

      public ServiceRuntimeException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
      }
}