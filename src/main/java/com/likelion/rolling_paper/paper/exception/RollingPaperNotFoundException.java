package com.likelion.rolling_paper.paper.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class RollingPaperNotFoundException extends BaseException {
    public RollingPaperNotFoundException() {
        super(RollingPaperErrorCode.ROLLING_PAPER_NOT_FOUND_404);
    }
}
