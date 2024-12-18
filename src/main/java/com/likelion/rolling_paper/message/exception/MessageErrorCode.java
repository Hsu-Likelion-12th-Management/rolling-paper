package com.likelion.rolling_paper.message.exception;

import static com.likelion.rolling_paper.util.constant.StaticValue.DUPLICATED;

import com.likelion.rolling_paper.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorCode implements BaseErrorCode {

    MESSAGE_ALREADY_EXIST_409(DUPLICATED, "MESSAGE_ALREADY_EXIST_409", "이 눈덩이에는 이미 작성했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
