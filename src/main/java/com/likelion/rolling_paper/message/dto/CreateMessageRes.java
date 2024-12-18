package com.likelion.rolling_paper.message.dto;

import com.likelion.rolling_paper.domain.Message;

public record CreateMessageRes(
        Long messageId,
        String content
) {
    public static CreateMessageRes of(Message message) {
        return new CreateMessageRes(message.getId(), message.getContent());
    }
}
