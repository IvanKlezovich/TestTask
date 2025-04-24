//package com.example.testtask.secure;
//
//import com.example.testtask.entity.User;
//import com.example.testtask.secure.dto.AuthResponse;
//import com.example.testtask.secure.dto.LoginRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class AuthenticationService {
//
//    private final CustomUserDetailService userDetailsService;
//    private final JWTUtil jwtUtil;
//
//    public AuthResponse login(LoginRequest request) {
//        User user = request.email() != null
//                ? userDetailsService.loadUserByEmail(request.email())
//                : userDetailsService.loadUserByPhone(request.phone());
//
//        return new AuthResponse(
//                jwtUtil.generateToken(user.getId())
//        );
//    }
//}
