package com.likelion.rolling_paper.util.oauth;

import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.util.jwt.util.JwtTokenProvider;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 5; // 5일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일

    private final JwtTokenProvider jwtTokenProvider;

    public AuthTokens generate(User user) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        // kakaoId를 JWT subject로 설정
        String subject = user.getKakaoId().toString();

        String accessToken = jwtTokenProvider.generate(subject, "ROLE_USER", accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, "ROLE_USER", refreshTokenExpiredAt);

        return AuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }
}
