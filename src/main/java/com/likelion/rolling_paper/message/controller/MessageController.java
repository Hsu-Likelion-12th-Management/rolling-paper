package com.likelion.rolling_paper.message.controller;

import com.likelion.rolling_paper.message.dto.CreateMessageReq;
import com.likelion.rolling_paper.message.dto.MessageInfoRes;
import com.likelion.rolling_paper.message.service.MessageService;
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
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public SuccessResponse<MessageInfoRes> createNewMessage(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody CreateMessageReq createMessageReq)
        {
            MessageInfoRes res = messageService.createNewMessage(createMessageReq, customOAuth2User.getUsername());
            return SuccessResponse.of(res);
        }
}
