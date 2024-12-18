package com.likelion.rolling_paper.paper.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class RollingPaperAlreadyExistException extends BaseException {
    public RollingPaperAlreadyExistException() {
        super(RollingPaperErrorCode.ROLLING_PAPER_ALREADY_EXIST_400);
    }
}
