package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;

public record GetRollingPaperIsFinishRes(
        Boolean isFinish
) {
    public static GetRollingPaperIsFinishRes of(RollingPaper rollingPaper) {
        return new GetRollingPaperIsFinishRes(rollingPaper.getIsFinish());
    }
}
