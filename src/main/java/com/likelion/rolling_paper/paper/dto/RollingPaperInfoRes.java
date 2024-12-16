package com.likelion.rolling_paper.paper.dto;

public record RollingPaperInfoRes(
        Long pageId,
        String name,
        State state
) {
    public static RollingPaperInfoRes of(Long id, String name, State state) {
        return new RollingPaperInfoRes(id, name, state);
    }
}
