package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.State;

public record CreatePaperRes(
        Long paperId,
        String name
) {
    public static CreatePaperRes of(RollingPaper paper) {
        return new CreatePaperRes(paper.getId(), paper.getName());
    }
}
