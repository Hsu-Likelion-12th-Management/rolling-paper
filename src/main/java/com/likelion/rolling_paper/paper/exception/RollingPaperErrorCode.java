package com.likelion.rolling_paper.paper.exception;

import static com.likelion.rolling_paper.util.constant.StaticValue.NOT_FOUND;

import com.likelion.rolling_paper.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RollingPaperErrorCode implements BaseErrorCode {

    PAPER_NOT_FOUND_EXCEPTION(NOT_FOUND, "PAPER_404", "존재하지 롤링 페이퍼 입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
