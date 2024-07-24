package com.example.hestesttask.service.imp;


import com.example.hestesttask.dto.AccountDto;
import com.example.hestesttask.entity.Account;
import com.example.hestesttask.exception.ServiceRuntimeException;
import com.example.hestesttask.repository.AccountRepository;
import com.example.hestesttask.service.AccountService;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AccountServiceImp implements AccountService {

      private final AccountRepository repository;
      private final ModelMapper mapper;

      @Autowired
      public AccountServiceImp(AccountRepository repository, ModelMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
      }

      @Override
      @Size(min = 1)
      public List<AccountDto> findAllAccounts() {
            List<Account> allAccounts = repository.findAll();
            List<AccountDto> allAccountsDto = allAccounts.stream()
                  .map(user -> mapper.map(user, AccountDto.class))
                  .toList();
            return allAccountsDto;
      }

      @Transactional(isolation = Isolation.REPEATABLE_READ)
      @Override
      public AccountDto blockAccountById(Long accountId) {
            Account account = getAccountByIdOrThrow(accountId);
            account.setIsBlocked(true);
            Account accountAfterBlocking = repository.save(account);
            return mapper.map(accountAfterBlocking, AccountDto.class);
      }

      @Override
      @Transactional(isolation = Isolation.REPEATABLE_READ)
      public AccountDto unblockAccountById(Long accountId) {
            Account account = getAccountByIdOrThrow(accountId);
            account.setIsBlocked(false);
            Account accountAfterBlocking = repository.save(account);
            return mapper.map(accountAfterBlocking, AccountDto.class);
      }

      private Account getAccountByIdOrThrow(Long accountId) {
            Optional<Account> optional = repository.findById(accountId);
            return optional.orElseThrow(
                  () -> new ServiceRuntimeException(404, "User with id : " + accountId + " not found" )
            );
      }
}
