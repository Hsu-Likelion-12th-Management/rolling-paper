package com.likelion.rolling_paper.message.dto;

import com.likelion.rolling_paper.domain.Message;

public record MessageInfoRes(
        Long messageId,
        String content
) {
    public static MessageInfoRes of(Message message) {
        return new MessageInfoRes(message.getId(), message.getContent());
    }
}
