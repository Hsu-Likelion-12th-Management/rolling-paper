package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.GetIsRollingPaperMadeRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperIsFinishRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperMessageListRes;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface RollingPaperService {
    CreateRollingPaperRes createRollingPaper(String kakaoId);
    List<GetRollingPaperListRes> getRollingPaperList();
    void getMessageWritingIsAvailable(String kakaoId, Long paperId);
    GetRollingPaperIsFinishRes getRollingPaperIsFinishRes(String kakaoId);
    void changeRollingPaperStatusToFinish(String kakaoId);
    List<GetRollingPaperMessageListRes> getRollingPaperMessageListRes(String kakaoId, Long paperId);
    GetIsRollingPaperMadeRes getIsRollingPaperMade(String kakaoId);
}
