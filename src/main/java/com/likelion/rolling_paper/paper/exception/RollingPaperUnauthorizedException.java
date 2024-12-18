package com.likelion.rolling_paper.paper.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class RollingPaperUnauthorizedException extends BaseException {
    public RollingPaperUnauthorizedException() {
        super(RollingPaperErrorCode.ROLLING_PAPER_UNAUTHORIZED_401);
    }
}
