package com.example.hestesttask.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionUserDto {
      @NotNull
      private Long userId;
      @NotNull
      private Long accountId;

      @DecimalMin(value = "0.0", inclusive = false)
      @Digits(integer = 5, fraction = 2)
      private BigDecimal amount;
}
