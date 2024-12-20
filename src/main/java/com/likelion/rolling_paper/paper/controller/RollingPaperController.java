package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.GetIsRollingPaperMadeRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperIsFinishRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperMessageListRes;
import com.likelion.rolling_paper.paper.service.RollingPaperService;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    /**
     * 내 눈덩이 작성이 종료되었는지 확인
     */
    @GetMapping("/finish")
    public SuccessResponse<GetRollingPaperIsFinishRes> getRollingPaperIsFinishRes(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        GetRollingPaperIsFinishRes res = rollingPaperService.getRollingPaperIsFinishRes(customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }

    /**
     * 내 눈덩이 작성 종료
     */
    @PutMapping("/finish")
    public SuccessResponse<Void> changeRollingPaperStatusToFinish(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    ) {
        rollingPaperService.changeRollingPaperStatusToFinish(customOAuth2User.getUsername());
        return SuccessResponse.of(null);
    }

    /**
     * 눈덩이에 작성된 메시지 리스트 조회
     */
    @GetMapping("/{paperId}/list")
    public SuccessResponse<List<GetRollingPaperMessageListRes>> getRollingPaperMessageRes(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable("paperId") Long paperId
    ) {
        List<GetRollingPaperMessageListRes> res = rollingPaperService.getRollingPaperMessageListRes(
                customOAuth2User.getUsername(),
                paperId
        );
        return SuccessResponse.of(res);
    }

    @GetMapping("/check")
    public SuccessResponse<GetIsRollingPaperMadeRes> getIsRollingPaperMade(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        GetIsRollingPaperMadeRes res = rollingPaperService.getIsRollingPaperMade(customOAuth2User.getUsername());
        return SuccessResponse.of(res);
    }
}
