package com.example.hestesttask.service;


import com.example.hestesttask.dto.AccountDto;

import java.util.List;

public interface AccountService {
      List<AccountDto> findAllAccounts();

      AccountDto blockAccountById(Long userId);

      AccountDto unblockAccountById(Long userId);
}
