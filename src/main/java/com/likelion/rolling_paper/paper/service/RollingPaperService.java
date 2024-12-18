package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.GetRollingPaperIsFinishRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import java.util.List;

public interface RollingPaperService {
    CreateRollingPaperRes createRollingPaper(String kakaoId);
    List<GetRollingPaperListRes> getRollingPaperList();
    void getMessageWritingIsAvailable(String kakaoId, Long paperId);
    GetRollingPaperIsFinishRes getRollingPaperIsFinishRes(String kakaoId);
    void changeRollingPaperStatusToFinish(String kakaoId);
}
