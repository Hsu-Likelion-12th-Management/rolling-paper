package com.likelion.rolling_paper.util.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String role;
    private String name;
    private String username; // 이메일로 username 대체
}