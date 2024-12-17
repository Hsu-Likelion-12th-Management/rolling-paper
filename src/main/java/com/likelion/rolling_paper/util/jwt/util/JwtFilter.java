package com.likelion.rolling_paper.util.jwt.util;

import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.jwt.dto.UserDto;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Bearer ")) {
                log.info("[JwtFilter] Token is null or invalid.");
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorization.split(" ")[1];

            if (jwtTokenProvider.isExpired(token)) {
                throw new IllegalArgumentException("Token is expired.");
            }

            String uuid = jwtTokenProvider.extractSubject(token);

            UserDto userDto = UserDto.builder()
                    .username(uuid)
                    .build();

            CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    customOAuth2User, null, customOAuth2User.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (Exception e) {
            log.error("[JwtFilter] Exception: {}", e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
