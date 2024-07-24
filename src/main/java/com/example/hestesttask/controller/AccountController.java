package com.example.hestesttask.controller;

import com.example.hestesttask.dto.AccountDto;
import com.example.hestesttask.dto.TransactionUserDto;
import com.example.hestesttask.exception.ServiceRuntimeException;
import com.example.hestesttask.service.AccountService;
import com.example.hestesttask.service.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/account")
@Validated
public class AccountController {

      private final AccountService accountService;

      private final UserService userService;

      @Autowired
      public AccountController(AccountService accountService, UserService userService) {
            this.accountService = accountService;
            this.userService = userService;
      }


      @GetMapping()
      @PreAuthorize("hasRole('ADMIN')")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
            ),
            @ApiResponse(
                  responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            )
      })
      public ResponseEntity<List<AccountDto>>getAllAccounts() {
            List<AccountDto> allAccounts = accountService.findAllAccounts();
            return ResponseEntity.ok(allAccounts);
      }

      @PutMapping("/{account_id}/block")
      @PreAuthorize("hasRole('ADMIN')")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
            ),
            @ApiResponse(
                  responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            )
      })
      public ResponseEntity<AccountDto> blockAccountById(@PathVariable @Min(0) Long account_id) {
            AccountDto accountDto = accountService.blockAccountById(account_id);
            return ResponseEntity.ok(accountDto);
      }

      @PutMapping("/{account_id}/unblock")
      @PreAuthorize("hasRole('ADMIN')")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
            ),
            @ApiResponse(
                  responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            )
      })
      public ResponseEntity<AccountDto> unblockAccountById(@PathVariable @Min(0) Long account_id) {
            AccountDto accountDto = accountService.unblockAccountById(account_id);
            return ResponseEntity.ok(accountDto);
      }

      @PutMapping("/deposit")
      @PreAuthorize("hasRole('OWNER')")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
            ),
            @ApiResponse(
                  responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            )
      })
      public ResponseEntity<AccountDto> depositUserAccountById(@Valid @RequestBody TransactionUserDto userDto) {
            AccountDto accountDto = userService
                  .depositUserAccountById(userDto.getUserId(), userDto.getAccountId(), userDto.getAmount()
                  );
            return ResponseEntity.ok(accountDto);
      }

      @PutMapping("/withdraw")
      @PreAuthorize("hasRole('OWNER')")
      @ApiResponses({
            @ApiResponse(
                  responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
            ),
            @ApiResponse(
                  responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            ),
            @ApiResponse(
                  responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRuntimeException.class))
            )
      })
      public ResponseEntity<AccountDto> withdrawUserAccountById(@Valid @RequestBody TransactionUserDto userDto) {
            AccountDto accountDto = userService
                  .withdrawUserAccountById(userDto.getUserId(), userDto.getAccountId(), userDto.getAmount()
                  );
            return ResponseEntity.ok(accountDto);
      }
}
