package com.likelion.rolling_paper.message.service;

import com.likelion.rolling_paper.message.dto.CreateMessageReq;
import com.likelion.rolling_paper.message.dto.MessageInfoRes;

public interface MessageService {
    MessageInfoRes createNewMessage(CreateMessageReq req, String kakaoId);
}
