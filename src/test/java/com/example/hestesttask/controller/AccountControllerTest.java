package com.example.hestesttask.controller;

import com.example.hestesttask.dto.TransactionUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountControllerTest {
      @Autowired
      private MockMvc mvc;

      @Autowired
      private ObjectMapper mapper;

      @Test
      @WithMockUser(username = "admin1", password = "admin1", roles = {"ADMIN"})
      void getAllAccounts() throws Exception {
            mvc
                  .perform(MockMvcRequestBuilders
                        .get("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                  )
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }

      @Test
      @WithMockUser(username = "admin1", password = "admin1", roles = {"ADMIN"})
      void blockAccountById() throws Exception {
            mvc
                  .perform(MockMvcRequestBuilders
                        .put("/api/v1/account/1/block")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                  )
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }

      @Test
      @WithMockUser(username = "admin1", password = "admin1", roles = {"ADMIN"})
      void unblockAccountById() throws Exception {
            mvc
                  .perform(MockMvcRequestBuilders
                        .put("/api/v1/account/1/unblock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                  )
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }

      @Test
      @WithMockUser(username = "user3", password = "user3", roles = {"OWNER"})
      void depositUserAccountById() throws Exception {
            TransactionUserDto transactionUserDto = new TransactionUserDto();
            transactionUserDto.setUserId(3L);
            transactionUserDto.setAccountId(1L);
            transactionUserDto.setAmount(new BigDecimal("300.00"));

            String transactionUserDtoJson = mapper.writeValueAsString(transactionUserDto);

            mvc
                  .perform(MockMvcRequestBuilders
                        .put("/api/v1/account/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionUserDtoJson)
                  )
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }

      @Test
      @WithMockUser(username = "user3", password = "user3", roles = {"OWNER"})
      void withdrawUserAccountById() throws Exception {
            TransactionUserDto transactionUserDto = new TransactionUserDto();
            transactionUserDto.setUserId(3L);
            transactionUserDto.setAccountId(1L);
            transactionUserDto.setAmount(new BigDecimal("300.00"));

            String transactionUserDtoJson = mapper.writeValueAsString(transactionUserDto);
            mvc
                  .perform(MockMvcRequestBuilders
                        .put("/api/v1/account/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionUserDtoJson)
                  )
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }
}