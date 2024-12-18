package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.util.jwt.dto.CustomOAuth2User;
import com.likelion.rolling_paper.util.response.ErrorResponse;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "눈덩이 페이지 관련", description = "눈덩이 페이지와 관련된 API")
public interface RollingPaperControllerApi {
    @Operation(summary = "내 눈덩이 페이지 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "timestamp": "2024-11-03T05:08:45.036657",
                                "isSuccess": true,
                                "code": "200",
                                "message": "호출에 성공하였습니다.",
                                "data": {
                                    "paperId": 1,
                                    "name": "강민서"
                                }
                            }
                            """),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "롤링페이퍼 이미 존재",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "timestamp": "2024-12-19T00:22:55.937412",
                                "isSuccess": false,
                                "code": "ROLLING_PAPER_ALREADY_EXIST_400",
                                "message": "이미 롤링페이퍼를 생성했습니다.",
                                "httpStatus": 400
                            }
                            """),
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    SuccessResponse<CreateRollingPaperRes> createRollingPaper(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    );

//    @Operation(summary = "눈덩이에 메시지 남기기")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공",
//                    content = @Content(mediaType = "application/json",
//                            examples = @ExampleObject(value = """
//                                    {
//                                        "timestamp": "2024-11-16T00:51:05.668577",
//                                        "isSuccess": true,
//                                        "code": "200",
//                                        "message": "호출에 성공하였습니다.",
//                                        "data": {
//                                            "paperId": 1,
//                                            "name": "이이름"
//                                        }
//                                    }
//                                    """),
//                            schema = @Schema(implementation = SuccessResponse.class)))
//    })
//    @PostMapping
//    SuccessResponse<MessageInfoRes> createNewMessage(
//            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
//            @RequestBody CreateMessageReq createMessageReq);
}
