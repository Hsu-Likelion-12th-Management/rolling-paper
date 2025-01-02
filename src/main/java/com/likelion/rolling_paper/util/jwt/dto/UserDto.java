package com.likelion.rolling_paper.util.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String role; // 역할 부여
    private String name;
    private String username; // kakaoId로 username 대체

    public void setRole(String role) {
        this.role = role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }
}
