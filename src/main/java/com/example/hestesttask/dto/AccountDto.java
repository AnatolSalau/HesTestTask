package com.example.hestesttask.dto;

import com.example.hestesttask.entity.enums.CurrencyType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountDto {
      @NotNull
      private Long id;
      @NotNull
      private Long userId;

      @Digits(integer = 5, fraction = 2)
      private BigDecimal balance;
      private Boolean isBlocked;
      private CurrencyType currencyType;
      private Set<TransactionDto> transactions = new HashSet<>();
}
