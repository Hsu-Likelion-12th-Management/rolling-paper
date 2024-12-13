package com.likelion.rolling_paper.home.controller;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
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
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "롤링 페이퍼 홈 관련", description = "롤링 페이퍼 홈과 관련된 API")
public interface HomeControllerApi {

    @Operation(summary = "새로운 롤링 페이퍼 홈 제작")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                    + "    \"timestamp\": \"2024-10-22T21:35:03.755865\",\n"
                                    + "    \"isSuccess\": true,\n"
                                    + "    \"code\": \"200\",\n"
                                    + "    \"message\": \"새로운 롤링 페이퍼 홈이 생성 되었습니다.\",\n"
                                    + "    \"data\": 1\n"
                                    + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping
    SuccessResponse<?> createRollingPaperHome(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody @Valid CreateHomeReqDto createHomeReqDto);

    @Operation(summary = "내가 참여중인 롤링페이퍼 불러오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "timestamp": "2024-11-16T00:51:05.668577",
                                        "isSuccess": true,
                                        "code": "200",
                                        "message": "호출에 성공하였습니다.",
                                        "data": [
                                            {
                                                "homeId": 1,
                                                "title": "멋쟁이사자처럼 12기"
                                            },
                                            {
                                                "homeId": 2,
                                                "title": "HSU 19학번 졸업"
                                            },
                                        ]
                                    }
                                    """),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping
    SuccessResponse<?> getMyRollingPaperHome(@AuthenticationPrincipal CustomOAuth2User customOAuth2User);
}

