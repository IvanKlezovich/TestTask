package com.example.testtask.secure;

import com.example.testtask.entity.User;
import com.example.testtask.secure.dto.AuthResponse;
import com.example.testtask.secure.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

  private final CustomUserDetailService userDetailsService;
  private final JWTUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.email(),
        request.password()));

    User user = userDetailsService.loadUserByEmail(request.email());

    return new AuthResponse(
        jwtUtil.generateToken(user.getId(), request.email())
    );
  }
}
