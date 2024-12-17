package com.likelion.rolling_paper.test;

import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthTestController {

    @GetMapping("/auth/test")
    public void authTest(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String username = customOAuth2User.getUsername();
        log.info("username : " + username);
    }
}
