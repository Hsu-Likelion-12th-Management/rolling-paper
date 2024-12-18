package com.likelion.rolling_paper.message.service;

import com.likelion.rolling_paper.message.dto.CreateMessageReq;
import com.likelion.rolling_paper.message.dto.CreateMessageRes;

public interface MessageService {
    CreateMessageRes createNewMessage(CreateMessageReq req, String kakaoId);
}
