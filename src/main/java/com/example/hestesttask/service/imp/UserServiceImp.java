package com.example.hestesttask.service.imp;

import com.example.hestesttask.dto.AccountDto;
import com.example.hestesttask.entity.Account;
import com.example.hestesttask.entity.Transaction;
import com.example.hestesttask.entity.User;
import com.example.hestesttask.entity.enums.TransactionType;
import com.example.hestesttask.exception.ServiceRuntimeException;
import com.example.hestesttask.repository.UserRepository;
import com.example.hestesttask.service.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
public class UserServiceImp implements UserService {

      private final UserRepository repository;

      private final ModelMapper mapper;

      @Autowired
      public UserServiceImp(UserRepository userRepository, ModelMapper mapper) {
            this.repository = userRepository;
            this.mapper = mapper;
      }

      @Override
      @Size(min = 1)
      public List<AccountDto> findAllUserAccountsById( Long userId) {
            User user = getUserByIdOrThrow(userId);
            return user.getAccounts().stream()
                  .map(account -> mapper.map(account, AccountDto.class))
                  .toList();
      }

      @Override
      @Transactional(isolation = Isolation.REPEATABLE_READ)
      public AccountDto depositUserAccountById(Long userId, Long accountId, BigDecimal amount) {
            User user = getUserByIdOrThrow(userId);
            Account account = findUserAccountOrThrow(user, accountId);
            checkAccountBlockingOrThrow(account);
            depositAccountBalance(account, amount);
            createDepositTransactionAndAddToAccount(account, amount);
            User userFromDb = repository.save(user);
            AccountDto accountDto = mapper.map(
                  findUserAccountOrThrow(userFromDb, accountId), AccountDto.class
            );
            return accountDto;
      }

      @Override
      @Transactional(isolation = Isolation.REPEATABLE_READ)
      public AccountDto withdrawUserAccountById(Long userId, Long accountId, BigDecimal amount) {
            User user = getUserByIdOrThrow(userId);
            Account account = findUserAccountOrThrow(user, accountId);
            checkAccountBlockingOrThrow(account);
            withdrawAccountBalance(account, amount);
            createWithdrawTransactionAndAddToAccount(account, amount);
            User userFromDb = repository.save(user);
            AccountDto accountDto = mapper.map(
                  findUserAccountOrThrow(userFromDb, accountId), AccountDto.class
            );
            return accountDto;
      }

      private User getUserByIdOrThrow(Long userId) {
            Optional<User> optional = repository.findById(userId);
            return optional.orElseThrow(
                  () -> new ServiceRuntimeException(404, "User with id : " + userId + " not found")
            );
      }

      private Account findUserAccountOrThrow(User user, Long accountId) {
            Account account = user.getAccounts().stream()
                  .filter(acc -> Objects.equals(acc.getId(), accountId))
                  .findFirst()
                  .orElseThrow(
                        () -> new ServiceRuntimeException(
                              404, "User (" + user.getName() + ") account with id : " + accountId + " not found"
                        )
                  );
            return account;
      }


      private void checkAccountBlockingOrThrow(Account account) {
            if (account.getIsBlocked()) {
                  throw new ServiceRuntimeException(
                        500, "Account with id : " + account.getId() + " is blocked"
                  );
            }
      }

      private void depositAccountBalance(Account account, BigDecimal amount) {
            BigDecimal balance = account.getBalance();
            BigDecimal newBalance = balance.add(amount);
            account.setBalance(newBalance);
      }

      private void withdrawAccountBalance(Account account, BigDecimal amount) {
            BigDecimal balance = account.getBalance();
            BigDecimal newBalance = balance.subtract(amount);
            account.setBalance(newBalance);
      }

      private void createDepositTransactionAndAddToAccount(Account account, BigDecimal amount) {
            Transaction newTransaction = new Transaction(
                  account.getId(), TransactionType.DEPOSIT,
                  amount, account.getCurrencyType()
            );
            account.getTransactions().add(newTransaction);
      }

      private void createWithdrawTransactionAndAddToAccount(Account account, BigDecimal amount) {
            Transaction newTransaction = new Transaction(
                  account.getId(), TransactionType.WITHDRAWAL,
                  amount, account.getCurrencyType()
            );
            account.getTransactions().add(newTransaction);
      }
}
