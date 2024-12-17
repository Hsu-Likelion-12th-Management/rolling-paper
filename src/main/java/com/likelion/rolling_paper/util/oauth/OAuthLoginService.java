package com.likelion.rolling_paper.util.oauth;

import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(String code) {
        // 인가 코드를 사용하여 KakaoLoginParams 생성
        KakaoLoginParams params = new KakaoLoginParams(code);
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .nickname(oAuthInfoResponse.getNickname())
                .profileImage(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        return userRepository.save(user).getId();
    }
}
