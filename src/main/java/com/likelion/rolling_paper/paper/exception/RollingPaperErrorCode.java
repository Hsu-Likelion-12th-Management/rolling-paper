package com.likelion.rolling_paper.paper.exception;

import static com.likelion.rolling_paper.util.constant.StaticValue.BAD_REQUEST;
import static com.likelion.rolling_paper.util.constant.StaticValue.NOT_FOUND;

import com.likelion.rolling_paper.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RollingPaperErrorCode implements BaseErrorCode {

    ROLLING_PAPER_ALREADY_EXIST_400(BAD_REQUEST, "ROLLING_PAPER_ALREADY_EXIST_400", "이미 롤링페이퍼를 생성했습니다."),
    ROLLING_PAPER_NOT_FOUND_404(NOT_FOUND, "ROLLING_PAPER_NOT_FOUND_404", "존재하지 않는 롤링페이퍼입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
