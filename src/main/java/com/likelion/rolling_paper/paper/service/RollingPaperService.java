package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;

public interface RollingPaperService {
    CreateRollingPaperRes createPaper(String kakaoId);

    MessageInfoRes createNewMessage(CreateMessageReq createMessageReq, String kakaoId);
}
