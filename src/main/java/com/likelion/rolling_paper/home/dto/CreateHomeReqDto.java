package com.likelion.rolling_paper.home.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateHomeReqDto(
        @NotBlank(message = "이름은 필수입니다.")
        String name,
        @NotBlank(message = "참여인원은 필수입니다.")
        Integer participantCount
) {
}
