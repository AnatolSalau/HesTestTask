package com.example.hestesttask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
      @NotNull
      private Long id;
      @NotNull
      private String name;

      private Date createdAt;

      private Date updatedAt;

      private Set<AccountDto> accounts = new HashSet<>();
}