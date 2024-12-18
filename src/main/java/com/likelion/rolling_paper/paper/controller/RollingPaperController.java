package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.service.RollingPaperService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/paper")
public class RollingPaperController implements RollingPaperControllerApi {
    private final RollingPaperService rollingPaperService;

    /**
     * 내 눈덩이 페이지 생성하기
     */
    @PostMapping
    public SuccessResponse<CreateRollingPaperRes> createRollingPaper(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        CreateRollingPaperRes res = rollingPaperService.createRollingPaper(customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }

//    @PostMapping("/message")
//    public SuccessResponse<MessageInfoRes> createNewMessage(
//            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
//            @RequestBody CreateMessageReq createMessageReq) {
//        MessageInfoRes res = rollingPaperService.createNewMessage(createMessageReq, customOAuth2User.getUsername());
//        return SuccessResponse.of(res);
//    }
}
