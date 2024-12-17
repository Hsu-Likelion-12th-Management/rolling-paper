package com.likelion.rolling_paper.domain;

import com.likelion.rolling_paper.util.entity.BaseEntity;
import com.likelion.rolling_paper.util.oauth.OAuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
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

    @Column(name = "nickname")
    private String nickname; // 카카오 로그인으로 받은 이름

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid; // 자동 생성 UUID

    @Column(name = "profile_image")
    private String profileImage; // 카카오 로그인으로 받은 프로필 이미지

    // KAKAO, NAVER.. ETC
    private OAuthProvider oAuthProvider;

    // UUID 자동 생성 설정
    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    @Builder
    public User(String nickname, String profileImage, OAuthProvider oAuthProvider) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.oAuthProvider = oAuthProvider;
    }
}
