package com.likelion.rolling_paper.message.controller;

import com.likelion.rolling_paper.message.dto.CreateMessageReq;
import com.likelion.rolling_paper.message.dto.CreateMessageRes;
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

@Tag(name = "메시지(사용자가 작성한 편지) 관련", description = "메시지(사용자가 작성한 편지)와 관련된 API")
public interface MessageControllerApi {

    @Operation(summary = "롤링페이퍼에 글 남기기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-11-03T05:08:45.036657",
                        "isSuccess": true,
                        "code": "200",
                        "message": "호출에 성공하였습니다.",
                        "data": {
                            "messageId": 1
                        }
                    }
                    """), schema = @Schema(implementation = SuccessResponse.class)))})
    @PostMapping
    SuccessResponse<CreateMessageRes> createNewMessage(
        @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
        @RequestBody CreateMessageReq createMessageReq
    );
}
