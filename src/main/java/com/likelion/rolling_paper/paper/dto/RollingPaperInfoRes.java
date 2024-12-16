package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.State;

public record RollingPaperInfoRes(
        Long pageId,
        String name,
        State state
) {
    public static RollingPaperInfoRes of(RollingPaper paper) {
        return new RollingPaperInfoRes(paper.getId(), paper.getName(), paper.getState());
    }
}
