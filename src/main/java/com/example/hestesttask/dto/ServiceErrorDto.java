package com.example.hestesttask.dto;

import lombok.Data;

@Data
public class ServiceErrorDto {
      private final int statusCode;
      private final String message;
}