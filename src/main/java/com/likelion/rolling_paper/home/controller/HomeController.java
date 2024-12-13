package com.likelion.rolling_paper.home.controller;

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
    @Override
    @PostMapping
    public SuccessResponse<?> createRollingPaperHome(Authentication authentication) {
        return null;
    }

    @Override
    @GetMapping("/all")
    public SuccessResponse<?> getMyRollingPaperHome(Authentication authentication) {
        return null;
    }
}
