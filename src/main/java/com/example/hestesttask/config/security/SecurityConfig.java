package com.example.hestesttask.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
      private final ServiceAuthenticationEntryPoint authEntryPoint;

      public SecurityConfig(ServiceAuthenticationEntryPoint authEntryPoint) {
            this.authEntryPoint = authEntryPoint;
      }

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                  .csrf(AbstractHttpConfigurer::disable)
                  .authorizeHttpRequests(req -> {
                        req.requestMatchers(antMatcher("/actuator/**")).permitAll();
                        req.requestMatchers(antMatcher("/swagger-ui/**")).permitAll();
                        req.requestMatchers(antMatcher("/api-docs/**")).permitAll();
                        req.anyRequest().authenticated();
                  })
                  .httpBasic(Customizer.withDefaults())
                  .exceptionHandling(
                        exception -> exception.authenticationEntryPoint(authEntryPoint)
                  );
            return http.build();
      }

      @Bean()
      public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
      }


}
