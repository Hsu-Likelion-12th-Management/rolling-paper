package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "눈덩이 페이지 관련", description = "눈덩이 페이지와 관련된 API")
public interface PageControllerApi {
    @Operation(summary = "내 눈덩이 페이지 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "timestamp": "2024-11-16T00:51:05.668577",
                                        "isSuccess": true,
                                        "code": "200",
                                        "message": "호출에 성공하였습니다.",
                                        "data": {
                                            "pageId": 1,
                                            "name": "이이름"
                                        },
                                    }
                                    """),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<RollingPaperInfoRes> createRollingPaperPage(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable Long homeId);

    @Operation(summary = "눈덩이에 메시지 남기기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "timestamp": "2024-11-16T00:51:05.668577",
                                        "isSuccess": true,
                                        "code": "200",
                                        "message": "호출에 성공하였습니다.",
                                        "data": {
                                            "pageId": 1,
                                            "name": "이이름"
                                        },
                                    }
                                    """),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<MessageInfoRes> createNewMessage(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable Long paperId);
}
