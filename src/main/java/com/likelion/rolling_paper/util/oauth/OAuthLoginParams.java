package com.likelion.rolling_paper.util.oauth;

import org.springframework.util.MultiValueMap;

// 로그인 API 요청 후 응답값을 리턴하는 인터페이스
public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
