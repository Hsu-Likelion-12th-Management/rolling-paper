package com.likelion.rolling_paper.paper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageReq(
        @NotNull(message = "아이디는 필수입니다.")
        Long paperId,
        @NotBlank(message = "메시지 내용은 비어있으면 안됩니다.")
        String content
) {
}
