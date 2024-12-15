package com.likelion.rolling_paper.domain;

import com.likelion.rolling_paper.util.entity.BaseEntity;
import com.likelion.rolling_paper.util.oauth.OAuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "kakao_id")
    private String kakaoId; // 카카오 로그인으로 받은 id

    @Column(name = "nickname")
    private String nickname; // 카카오 로그인으로 받은 이름

    @Column(name = "profile_image")
    private String profileImage; // 카카오 로그인으로 받은 프로필 이미지

    // KAKAO, NAVER.. ETC
    private OAuthProvider oAuthProvider;

    @Builder
    public User(String kakaoId, String nickname, OAuthProvider oAuthProvider) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
