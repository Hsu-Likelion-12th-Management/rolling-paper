package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.paper.service.PaperService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/paper/")
public class PageController implements PageControllerApi{
    private final PaperService paperService;

    @Override
    @PostMapping("{homeId}/make")
    public SuccessResponse<RollingPaperInfoRes> createRollingPaperPage(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable("homeId") Long homeId) {
        RollingPaperInfoRes res = paperService.createRollingPaperPage(homeId, customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }
}
