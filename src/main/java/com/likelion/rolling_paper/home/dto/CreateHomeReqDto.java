package com.likelion.rolling_paper.home.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateHomeReqDto(
        @NotBlank(message = "이름은 필수입니다.")
        String name,
        @NotNull(message = "참여인원은 필수입니다.")
        Integer participantCount
) {
}
