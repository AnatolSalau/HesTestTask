package com.example.hestesttask.dto;

import com.example.hestesttask.validation.Violation;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class ValidationErrorDto extends ServiceErrorDto {
      public ValidationErrorDto(int statusCode, String message, List<Violation> violations) {
            super(statusCode, message);
            this.violations = violations;
      }

      public final List<Violation> violations;

}