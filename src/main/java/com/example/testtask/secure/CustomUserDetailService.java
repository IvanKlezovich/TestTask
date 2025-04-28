package com.example.testtask.secure;

import com.example.testtask.entity.User;
import com.example.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService {

  private final UserRepository userRepository;

  public User loadUserByEmail(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
  }

  public UserDetailsService userDetailsService() {
    return this::loadUserByEmail;
  }
}
