package com.likelion.rolling_paper.user.exception;


import com.likelion.rolling_paper.util.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND_EXCEPTION);
    }
}
