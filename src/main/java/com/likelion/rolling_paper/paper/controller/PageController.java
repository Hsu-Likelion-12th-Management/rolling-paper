package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController implements PageControllerApi{
    @Override
    public SuccessResponse<RollingPaperInfoRes> createRollingPaperPage(CustomOAuth2User customOAuth2User) {
        return null;
    }
}
