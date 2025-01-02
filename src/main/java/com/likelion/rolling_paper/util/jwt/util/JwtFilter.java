package com.likelion.rolling_paper.util.jwt.util;

import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.jwt.dto.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authorization = request.getHeader("Authorization");
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                log.info("[JwtFilter] 1. Authorization 헤더 확인");
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorization.substring(7);

            if (jwtTokenProvider.isExpired(token)) {
                log.info("[JwtFilter] 토큰 만료됨");
                throw new IllegalArgumentException("Token is expired.");
            }

            String kakaoId = jwtTokenProvider.extractSubject(token);
            String role = jwtTokenProvider.extractRole(token);

            UserDto userDto = UserDto.builder()
                    .username(kakaoId)
                    .role(role)
                    .build();

            CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    customOAuth2User, null, customOAuth2User.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

            log.info("[JwtFilter] 유저에 대해 인증 성공, kakaoId: {}", kakaoId);

        } catch (ExpiredJwtException e) {
            log.error("[JwtFilter] catch 토큰 만료: {}", e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has expired");
            return;
        } catch (Exception e) {
            log.error("[JwtFilter] catch 토큰 검증: {}", e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
