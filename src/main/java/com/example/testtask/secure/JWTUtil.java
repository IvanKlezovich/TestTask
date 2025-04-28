package com.example.testtask.secure;

import com.example.testtask.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

  private static final long TOKEN_VALIDITY = 60 * 60 * 1000; // 24 часа
  @Value("${token.signing.key}")
  private String secretKey;

  public String generateToken(Long userId, String email) {
    Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);

    return Jwts.builder()
        .setClaims(Map.of("userId", userId))
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(expirationDate)
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, User user) {
    final String email = extractEmail(token);
    return (user.getEmailData().stream()
        .anyMatch(emailData -> emailData.getEmail().contains(email)))
        && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(getSigningKey())
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
