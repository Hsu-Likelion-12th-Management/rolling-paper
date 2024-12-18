package com.likelion.rolling_paper.message.dto;

import com.likelion.rolling_paper.domain.Message;

public record CreateMessageRes(
        Long messageId
) {
    public static CreateMessageRes of(Message message) {
        return new CreateMessageRes(message.getId());
    }
}
