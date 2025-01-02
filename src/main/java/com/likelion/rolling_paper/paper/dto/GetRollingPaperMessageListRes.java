package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.Message;
import java.util.List;
import java.util.stream.Collectors;

public record GetRollingPaperMessageListRes(
        Integer randomEmojiValue,
        String content,
        String name
) {
    public static GetRollingPaperMessageListRes of(Message message) {
        return new GetRollingPaperMessageListRes(
                message.getRandomEmojiValue(),
                message.getContent(),
                message.getUser().getNickname()
        );
    }

    public static List<GetRollingPaperMessageListRes> ofList(List<Message> messages) {
        return messages.stream()
                .map(GetRollingPaperMessageListRes::of)
                .collect(Collectors.toList());
    }
}
