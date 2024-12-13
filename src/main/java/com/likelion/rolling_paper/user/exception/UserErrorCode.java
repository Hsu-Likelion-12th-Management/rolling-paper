package com.likelion.rolling_paper.user.exception;

import static com.likelion.rolling_paper.util.constant.StaticValue.NOT_FOUND;

import com.likelion.rolling_paper.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND_EXCEPTION(NOT_FOUND, "USER_404", "존재하지 회원입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
