package com.likelion.rolling_paper.home.dto;

import com.likelion.rolling_paper.domain.RollingPaperHome;

public record GetMyRollingPaperHomeRes(
        Long homeId,
        String title
) {
        public static GetMyRollingPaperHomeRes of(RollingPaperHome rollingPaperHome) {
                return new GetMyRollingPaperHomeRes(
                        rollingPaperHome.getId(),
                        rollingPaperHome.getName());
        }
}
