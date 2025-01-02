package com.likelion.rolling_paper.paper.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class RollingPaperNotAvailableException extends BaseException {
    public RollingPaperNotAvailableException() {
        super(RollingPaperErrorCode.ROLLING_PAPER_NOT_AVAILABLE_400);
    }
}
