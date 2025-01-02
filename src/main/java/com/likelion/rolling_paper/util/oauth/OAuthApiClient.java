package com.likelion.rolling_paper.util.oauth;

public interface OAuthApiClient {
    // Client 타입 반환
    OAuthProvider oAuthProvider();

    // Authorization Code 기반으로 Access Token 획득
    String requestAccessToken(OAuthLoginParams params);

    // Access Token 기반으로 nickname 등이 포함된 프로필 정보 획득
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
