package com.likelion.rolling_paper.util.oauth;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/kakao/callback")
    public void loginKakao(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        log.info("[api/auth/kakao] 요청 들어옴");
        AuthTokens res = oAuthLoginService.login(code);

        // 프론트엔드로 리다이렉트하면서 JWT 토큰 전달
        String frontendUrl = "http://localhost:5173/home?token=" + res.getAccessToken();
        response.sendRedirect(frontendUrl);
    }
}