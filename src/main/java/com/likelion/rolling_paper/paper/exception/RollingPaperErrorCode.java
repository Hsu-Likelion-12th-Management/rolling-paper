package com.likelion.rolling_paper.paper.exception;

import static com.likelion.rolling_paper.util.constant.StaticValue.BAD_REQUEST;
import static com.likelion.rolling_paper.util.constant.StaticValue.NOT_FOUND;
import static com.likelion.rolling_paper.util.constant.StaticValue.UNAUTHORIZED;

import com.likelion.rolling_paper.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RollingPaperErrorCode implements BaseErrorCode {

    ROLLING_PAPER_ALREADY_EXIST_400(BAD_REQUEST, "ROLLING_PAPER_ALREADY_EXIST_400", "이미 롤링페이퍼를 생성했습니다."),
    ROLLING_PAPER_NOT_AVAILABLE_400(BAD_REQUEST, "ROLLING_PAPER_NOT_AVAILABLE_400", "눈덩이 주인이 메시지 작성을 종료하였습니다."),
    ROLLING_PAPER_UNAUTHORIZED_401(UNAUTHORIZED, "ROLLING_PAPER_UNAUTHORIZED_401", "눈덩이 주인 본인은 작성할 수 없습니다."),
    ROLLING_PAPER_OPEN_UNAUTHORIZED_401(UNAUTHORIZED, "ROLLING_PAPER_OPEN_UNAUTHORIZED_401", "본인 눈덩이만 열 수 있습니다."),
    ROLLING_PAPER_NOT_FOUND_404(NOT_FOUND, "ROLLING_PAPER_NOT_FOUND_404", "존재하지 않는 롤링페이퍼입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
