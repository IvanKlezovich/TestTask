//package com.example.testtask.secure;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@AllArgsConstructor
//public class AuthFilter extends OncePerRequestFilter {
//
//    private final JWTUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            try {
//                Long userId = jwtUtil.validateToken(token.substring(7));
//                request.setAttribute("userId", userId);
//            } catch (Exception e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Неверный токен");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
