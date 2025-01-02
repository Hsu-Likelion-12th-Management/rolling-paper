package com.likelion.rolling_paper.paper.dto;

import com.likelion.rolling_paper.domain.RollingPaper;
import java.util.List;
import java.util.stream.Collectors;

public record GetRollingPaperListRes(
        Long paperId,
        String name,
        boolean isFinish
) {
    public static GetRollingPaperListRes of(RollingPaper paper) {
        return new GetRollingPaperListRes(
                paper.getId(),
                paper.getName(),
                paper.getIsFinish()
        );
    }

    public static List<GetRollingPaperListRes> ofList(List<RollingPaper> papers) {
        return papers.stream()
                .map(GetRollingPaperListRes::of)
                .collect(Collectors.toList());
    }
}
