package com.likelion.rolling_paper.util.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoInfoResponse implements OAuthInfoResponse{
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Override
    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    @Override
    public String getNickname() {
        return kakaoAccount.getProfile().getNickname();
    }

    @Override
    public String getProfileImage() {
        return kakaoAccount.getProfile().getProfileImageUrl();
    }

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.KAKAO;
    }
}

@Getter
@Setter
class KakaoAccount {
    private String email;

    private Profile profile;
}

@Getter
@Setter
class Profile {
    private String nickname;

    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;
}
