package com.example.hestesttask.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
      @Bean
      public GroupedOpenApi userOpenApi() {
            String paths[] = {"/api/v1/user/**"};
            return GroupedOpenApi
                  .builder()
                  .group("User")
                  .pathsToMatch(paths)
                  .build();
      }

      @Bean
      public GroupedOpenApi accountOpenApi() {
            String paths[] = {"/api/v1/account/**"};
            return GroupedOpenApi
                  .builder()
                  .group("Account")
                  .pathsToMatch(paths)
                  .build();
      }

      @Bean
      public GroupedOpenApi actuatorOpenApi() {
            String paths[] = {"/actuator", "/actuator/info", "/actuator/health"};
            return GroupedOpenApi
                  .builder()
                  .group("Actuator")
                  .pathsToMatch(paths)
                  .build();
      }
}
