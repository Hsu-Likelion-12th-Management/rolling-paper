package com.likelion.rolling_paper.home.service;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.util.response.SuccessResponse;

public interface HomeService {
    SuccessResponse<?> createRollingPaperHome(String email, CreateHomeReqDto createHomeReqDto);
    SuccessResponse<?> getMyRollingPaperHomes(String email);
}
