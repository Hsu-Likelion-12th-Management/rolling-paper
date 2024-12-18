package com.likelion.rolling_paper.message.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class MessageAlreadyExistException extends BaseException {
    public MessageAlreadyExistException() {
        super(MessageErrorCode.MESSAGE_ALREADY_EXIST_409);
    }
}
