package com.likelion.rolling_paper.home.service;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public SuccessResponse<?> createRollingPaperHome(String email, CreateHomeReqDto createHomeReqDto) {
        return null;
    }

    @Override
    public SuccessResponse<?> getMyRollingPaperHomes(String email) {
        return null;
    }
}
