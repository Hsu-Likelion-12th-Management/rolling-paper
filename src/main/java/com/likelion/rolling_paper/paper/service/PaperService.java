package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;

public interface PaperService {
    RollingPaperInfoRes createRollingPaperPage(String kakaoId);

    MessageInfoRes createNewMessage(CreateMessageReq createMessageReq, String kakaoId);
}
