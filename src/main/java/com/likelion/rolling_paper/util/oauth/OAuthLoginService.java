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

    /**
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
        log.info("oAuthInfoResponse.nickname : " + oAuthInfoResponse.getNickname());
        log.info("oAuthInfoResponse.profileImage : " + oAuthInfoResponse.getProfileImage());

        User user = User.builder()
                .nickname(oAuthInfoResponse.getNickname())
                .profileImage(oAuthInfoResponse.getProfileImage())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        return userRepository.save(user).getId();
    }
     **/

    public AuthTokens login(String code) {
        // OAuth 인증을 통해 사용자 정보를 가져옴
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(new KakaoLoginParams(code));

        // UUID 기반으로 사용자 찾기 또는 신규 생성
        UUID uuid = UUID.randomUUID(); // 새 UUID 생성 (OAuth 제공자에서 관리하지 않으므로 새로 생성)

        User user = userRepository.findByUuid(uuid)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .nickname(oAuthInfoResponse.getNickname())
                                .profileImage(oAuthInfoResponse.getProfileImage())
                                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                                .build()
                ));

        return authTokensGenerator.generate(user);
    }
}
