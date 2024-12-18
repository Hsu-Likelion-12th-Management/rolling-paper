package com.likelion.rolling_paper.paper.controller;

import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperIsFinishRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
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
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "눈덩이 페이지 관련", description = "눈덩이 페이지와 관련된 API")
public interface RollingPaperControllerApi {
    @Operation(summary = "내 눈덩이 페이지 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
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
                    """), schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "롤링페이퍼 이미 존재", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T00:22:55.937412",
                        "isSuccess": false,
                        "code": "ROLLING_PAPER_ALREADY_EXIST_400",
                        "message": "이미 롤링페이퍼를 생성했습니다.",
                        "httpStatus": 400
                    }
                    """), schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping
    SuccessResponse<CreateRollingPaperRes> createRollingPaper(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User);

    @Operation(summary = "눈덩이 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                        {
                            "timestamp": "2024-11-03T05:08:45.036657",
                            "isSuccess": true,
                            "code": "200",
                            "message": "호출에 성공하였습니다.",
                            "data": [
                                {
                                    "paperId": 1,
                                    "name": "강민서",
                                    "isFinish" : false
                                },
                                {
                                    "paperId": 2,
                                    "name": "이나경",
                                    "isFinish" : false
                                }
                            ]
                        }
                    """), schema = @Schema(implementation = SuccessResponse.class)))})
    @GetMapping("/list")
    SuccessResponse<List<GetRollingPaperListRes>> getRollingPaperList(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User);

    @Operation(summary = "눈덩이 메시지 작성 가능 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "작성 가능",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T01:35:48.578204",
                        "isSuccess": true,
                        "code": "200",
                        "message": "호출에 성공하였습니다.",
                        "data": null
                    }
                    """),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "눈덩이 주인이 메시지 작성을 종료",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T01:41:09.799386",
                        "isSuccess": false,
                        "code": "ROLLING_PAPER_NOT_AVAILABLE_400",
                        "message": "눈덩이 주인이 메시지 작성을 종료하였습니다.",
                        "httpStatus": 400
                    }
                    """),
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "눈덩이 주인 본인은 작성 불가",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T01:41:09.799386",
                        "isSuccess": false,
                        "code": "ROLLING_PAPER_NOT_AVAILABLE_400",
                        "message": "눈덩이 주인 본인은 작성할 수 없습니다.",
                        "httpStatus": 401
                    }
                    """),
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 롤링페이퍼에 접근",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T01:36:11.930022",
                        "isSuccess": false,
                        "code": "ROLLING_PAPER_NOT_FOUND_404",
                        "message": "존재하지 않는 롤링페이퍼입니다.",
                        "httpStatus": 404
                    }
                    """),
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "이미 작성한 롤링페이퍼에는 중복 작성 불가",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                        "timestamp": "2024-12-19T01:42:36.452182",
                        "isSuccess": false,
                        "code": "MESSAGE_ALREADY_EXIST_409",
                        "message": "이 눈덩이에는 이미 작성했습니다.",
                        "httpStatus": 409
                    }
                    """),
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{paperId}/check")
    SuccessResponse<Void> getMessageWritingIsAvailable(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable("paperId") Long paperId
    );

    @Operation(summary = "내 눈덩이 작성이 종료되었는지 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "눈덩이 작성 종료 여부 조회 성공 - 메시지 작성 종료 상태(true), 메시지 작성 가능 상태(false)", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                        {
                            "timestamp": "2024-11-03T05:08:45.036657",
                            "isSuccess": true,
                            "code": "200",
                            "message": "호출에 성공하였습니다.",
                            "data": {
                                "isFinish": true
                            }
                        }
                    """), schema = @Schema(implementation = SuccessResponse.class)))})
    @GetMapping("/finish")
    SuccessResponse<GetRollingPaperIsFinishRes> getRollingPaperIsFinishRes(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User
    );

    @Operation(summary = "내 눈덩이 작성 종료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "눈덩이 작성 종료 성공", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                        {
                            "timestamp": "2024-11-03T05:08:45.036657",
                            "isSuccess": true,
                            "code": "200",
                            "message": "호출에 성공하였습니다.",
                            "data": null
                        }
                    """), schema = @Schema(implementation = SuccessResponse.class)))})
    @PutMapping("/finish")
    SuccessResponse<Void> changeRollingPaperStatusToFinish(@AuthenticationPrincipal CustomOAuth2User customOAuth2User);
}
