package com.likelion.rolling_paper.home.controller;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.home.service.HomeService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController implements HomeControllerApi{
    private final HomeService homeService;
    @Override
    @PostMapping
    public SuccessResponse<?> createRollingPaperHome(CustomOAuth2User customOAuth2User,
                                                     CreateHomeReqDto createHomeReqDto) {
        return homeService.createRollingPaperHome(customOAuth2User.getUsername(), createHomeReqDto);
    }

    @Override
    public SuccessResponse<?> getMyRollingPaperHome(CustomOAuth2User customOAuth2User) {
        return null;
    }
}
