package com.likelion.rolling_paper.util.exception;

public interface BaseErrorCode {

    String getCode();
    String getMessage();
    int getHttpStatus();
}
