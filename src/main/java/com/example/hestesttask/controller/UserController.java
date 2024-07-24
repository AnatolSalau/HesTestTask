package com.example.hestesttask.controller;

import com.example.hestesttask.dto.AccountDto;
import com.example.hestesttask.exception.ServiceRuntimeException;
import com.example.hestesttask.service.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {

      private final UserService userService;

      @Autowired
      public UserController(UserService userService) {
            this.userService = userService;
      }

      @GetMapping("{user_id}/account")
      @PreAuthorize("hasRole('OWNER')")
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
      public ResponseEntity<List<AccountDto>> getUserAccounts(@PathVariable @Min(0) Long user_id) {
            List<AccountDto> allUserAccounts = userService.findAllUserAccountsById(user_id);
            return ResponseEntity.ok(allUserAccounts);
      }
}