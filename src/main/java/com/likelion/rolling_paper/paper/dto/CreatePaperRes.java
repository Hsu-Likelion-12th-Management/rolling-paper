package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.State;

public record CreatePaperRes(
        Long pageId,
        String name,
        State state
) {
    public static CreatePaperRes of(RollingPaper paper) {
        return new CreatePaperRes(paper.getId(), paper.getName(), paper.getState());
    }
}
