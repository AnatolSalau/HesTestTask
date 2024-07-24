package com.example.hestesttask.config.security;


import com.example.hestesttask.entity.User;
import com.example.hestesttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
      private final UserRepository repository;

      @Autowired
      public UserDetailServiceImpl(UserRepository repository) {
            this.repository = repository;
      }

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findUserByName(username).orElseThrow(
                  () -> new UsernameNotFoundException(username)
            );
            UserDetails userDetails = new UserDetailsImpl(
                  user.getName(), user.getPassword(), user.getRoleType().name()
            );
            return userDetails;
      }
}
