package com.likelion.rolling_paper.home.controller;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.home.dto.GetMyRollingPaperHomeRes;
import com.likelion.rolling_paper.home.service.HomeService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController implements HomeControllerApi {
    private final HomeService homeService;

    @Override
    @PostMapping
    public SuccessResponse<Long> createRollingPaperHome(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody @Valid CreateHomeReqDto createHomeReqDto) {
        Long res = homeService.createRollingPaperHome(customOAuth2User.getUsername(),
                createHomeReqDto);
        return SuccessResponse.of(res);
    }

    @Override
    @GetMapping("/my")
    public SuccessResponse<List<GetMyRollingPaperHomeRes>> getMyRollingPaperHome(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            Pageable pageable) {
        List<GetMyRollingPaperHomeRes> res = homeService.getMyRollingPaperHomes(customOAuth2User.getUsername(), pageable);
        return SuccessResponse.of(res);
    }
}
