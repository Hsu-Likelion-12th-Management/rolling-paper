package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.paper.service.PaperService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/paper")
//public class PaperController implements PaperControllerApi {
public class PaperController {
    private final PaperService paperService;

    /**
     * 내 눈덩이 페이지 생성하기
     */
    @PostMapping
    public SuccessResponse<RollingPaperInfoRes> createPaper(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        RollingPaperInfoRes res = paperService.createRollingPaperPage(customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }

    @PostMapping("/message")
    public SuccessResponse<MessageInfoRes> createNewMessage(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody CreateMessageReq createMessageReq) {
        MessageInfoRes res = paperService.createNewMessage(createMessageReq, customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }
}
