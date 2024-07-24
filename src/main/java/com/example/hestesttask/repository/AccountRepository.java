package com.example.hestesttask.repository;

import com.example.hestesttask.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
