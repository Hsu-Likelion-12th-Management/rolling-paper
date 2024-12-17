package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.State;

public record MessageInfoRes(
        Long messageId,
        String content
) {
    public static MessageInfoRes of(Message message) {
        return new MessageInfoRes(message.getId(), message.getContent());
    }
}
