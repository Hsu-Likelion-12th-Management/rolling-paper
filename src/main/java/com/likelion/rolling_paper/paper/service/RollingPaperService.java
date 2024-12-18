package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import java.util.List;

public interface RollingPaperService {
    CreateRollingPaperRes createRollingPaper(String kakaoId);
    List<GetRollingPaperListRes> getRollingPaperList();
    void getMessageWritingIsAvailable(String kakaoId, Long paperId);
    void changeRollingPaperStatusToFinish(String kakaoId);

    MessageInfoRes createNewMessage(CreateMessageReq createMessageReq, String kakaoId);
}
