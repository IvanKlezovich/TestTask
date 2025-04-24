//package com.example.testtask.secure;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JWTUtil {
//    private static final String SECRET_KEY = "your-secret-key-here";
//    private static final long TOKEN_VALIDITY = 24 * 60 * 60 * 1000; // 24 часа
//
//    public String generateToken(Long userId) {
//        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);
//
//        return Jwts.builder()
//                .setSubject(String.valueOf(userId))
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    public Long validateToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Long.parseLong(claims.getSubject());
//    }
//}
