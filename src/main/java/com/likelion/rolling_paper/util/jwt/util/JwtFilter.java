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

    /**
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            log.info("[JwtFilter 클래스][doFilterInternal 메소드] token is null ");
            filterChain.doFilter(request, response);

            // 조건이 해당되면 메소드 종료 (필수)
            return;
        }

        // 토큰
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if (jwtTokenProvider.isExpired(token)) {

            log.info("[JwtFilter 클래스][doFilterInternal 메소드] token is expired ");
            filterChain.doFilter(request, response);

            // 조건이 해당되면 메소드 종료 (필수)
            return;
        }

//        // 토큰에서 username & role 획득
//        String username = jwtTokenProvider.getUsername(token);
////        String role = jwtTokenProvider.getRole(token);
//
//        // userDto 생성하여 값 set
//        UserDto userDto = UserDto.builder()
//                .username(username)
////                .role(role)
//                .build();

        String uuid = jwtTokenProvider.extractSubject(token); // subject에서 UUID 추출
        UserDto userDto = UserDto.builder()
                .username(uuid)  // username에 UUID 설정
                .build();

        // UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customOAuth2User,
                null,
                customOAuth2User.getAuthorities()
        );

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
    **/

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
