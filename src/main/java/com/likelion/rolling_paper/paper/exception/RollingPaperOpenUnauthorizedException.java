package com.likelion.rolling_paper.paper.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class RollingPaperOpenUnauthorizedException extends BaseException {
    public RollingPaperOpenUnauthorizedException() {
        super(RollingPaperErrorCode.ROLLING_PAPER_OPEN_UNAUTHORIZED_401);
    }
}
