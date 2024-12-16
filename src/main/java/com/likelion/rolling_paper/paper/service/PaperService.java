package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;

public interface PaperService {
    RollingPaperInfoRes createRollingPaperPage(Long homeId,String kakaoId);
}
