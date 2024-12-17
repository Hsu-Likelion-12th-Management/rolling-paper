package com.likelion.rolling_paper.util.oauth;

import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(String code) {
        // OAuth 인증을 통해 사용자 정보를 가져옴
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(new KakaoLoginParams(code));
        // Provider 타입에 따라 회원 가입 로직 수정 - 현재는 카카오만 지원
        if (oAuthInfoResponse.getOAuthProvider() == OAuthProvider.KAKAO) {
            KakaoInfoResponse kakaoInfoResponse = (KakaoInfoResponse) oAuthInfoResponse;
            User user = userRepository.findByKakaoId(String.valueOf(kakaoInfoResponse.getId()))
                    .orElseGet(() -> userRepository.save(
                            User.builder()
                                    .nickname(oAuthInfoResponse.getNickname())
                                    .profileImage(oAuthInfoResponse.getProfileImage())
                                    .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                                    .build()
                    ));
            return authTokensGenerator.generate(user);
        }
        return null;
    }
}
