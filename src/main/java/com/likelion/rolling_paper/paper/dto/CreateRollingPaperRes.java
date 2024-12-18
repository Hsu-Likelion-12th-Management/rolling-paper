package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.RollingPaper;

public record CreateRollingPaperRes(
        Long paperId,
        String name
) {
    public static CreateRollingPaperRes of(RollingPaper paper) {
        return new CreateRollingPaperRes(paper.getId(), paper.getName());
    }
}
