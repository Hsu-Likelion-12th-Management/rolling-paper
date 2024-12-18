package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.service.RollingPaperService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paper")
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

    /**
     * 눈덩이 리스트 조회
     */
    @GetMapping("/list")
    public SuccessResponse<List<GetRollingPaperListRes>> getRollingPaperList(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        List<GetRollingPaperListRes> res = rollingPaperService.getRollingPaperList();
        return SuccessResponse.of(res);
    }

    /**
     * 눈덩이에 메시지 작성 가능 여부 확인
     */
    @GetMapping("/{paperId}/check")
    public SuccessResponse<Void> getMessageWritingIsAvailable(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable("paperId") Long paperId
    ) {
        rollingPaperService.getMessageWritingIsAvailable(customOAuth2User.getUsername(), paperId);
        return SuccessResponse.of(null);
    }

//    @PostMapping("/message")
//    public SuccessResponse<MessageInfoRes> createNewMessage(
//            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
//            @RequestBody CreateMessageReq createMessageReq) {
//        MessageInfoRes res = rollingPaperService.createNewMessage(createMessageReq, customOAuth2User.getUsername());
//        return SuccessResponse.of(res);
//    }
}
