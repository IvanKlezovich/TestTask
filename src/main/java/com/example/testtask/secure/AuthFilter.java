package com.example.testtask.secure;

import com.example.testtask.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

  public static final String HEADER_NAME = "Authorization";
  public static final String BEARER_PREFIX = "Bearer ";
  private final CustomUserDetailService userService;
  private final JWTUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String token = request.getHeader(HEADER_NAME);
    if (StringUtils.isEmpty(token) || !StringUtils.startsWith(token, BEARER_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    var jwt = token.substring(BEARER_PREFIX.length());
    var userId = jwtUtil.extractEmail(jwt);
    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User user = userService.loadUserByEmail(userId);
      if (jwtUtil.isTokenValid(jwt, user)) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            user,
            null,
            user.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }

    filterChain.doFilter(request, response);
  }
}
