//package com.example.testtask.config;
//
//import com.example.testtask.secure.AuthFilter;
//import com.example.testtask.secure.JWTUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//  private final JWTUtil jwtUtil;
//
//  @Value("${public-api}")
//  private String[] publicApi;
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers(publicApi).permitAll()
//                    .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .addFilterBefore(new AuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
//          throws Exception {
//    return authConfig.getAuthenticationManager();
//  }
//}
