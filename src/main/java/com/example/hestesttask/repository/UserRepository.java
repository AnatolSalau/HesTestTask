package com.example.hestesttask.repository;


import com.example.hestesttask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
      @Override
      @Transactional
      <S extends User> S save(S entity);

      Optional<User> findUserByName(String name);
}
