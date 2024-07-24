package com.example.hestesttask.service;


import com.example.hestesttask.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

      List<AccountDto> findAllUserAccountsById(Long userId);

      AccountDto depositUserAccountById(Long userId, Long accountId, BigDecimal amount);

      AccountDto withdrawUserAccountById(Long userId, Long accountId, BigDecimal amount);
}