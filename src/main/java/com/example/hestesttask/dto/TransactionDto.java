package com.example.hestesttask.dto;

import com.example.hestesttask.entity.enums.CurrencyType;
import com.example.hestesttask.entity.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
      private TransactionType transactionType;

      @DecimalMin(value = "0.0", inclusive = false)
      @Digits(integer = 5, fraction = 2)
      private BigDecimal amount;

      private CurrencyType currencyType;
      private Date createdAt;
}