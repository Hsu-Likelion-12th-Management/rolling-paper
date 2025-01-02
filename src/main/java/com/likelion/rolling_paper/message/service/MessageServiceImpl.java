package com.likelion.rolling_paper.message.service;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.message.dto.CreateMessageReq;
import com.likelion.rolling_paper.message.dto.CreateMessageRes;
import com.likelion.rolling_paper.repository.MessageRepository;
import com.likelion.rolling_paper.repository.RollingPaperRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final MessageRepository messageRepository;

    @Override
    public CreateMessageRes createNewMessage(CreateMessageReq createMessageReq, String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaper paper = rollingPaperRepository.getById(createMessageReq.paperId());
        int randomEmojiValue = getRandomInteger();
        Message message = messageRepository.save(Message.toEntity(user, createMessageReq.content(), randomEmojiValue, paper));
        return CreateMessageRes.of(message);
    }

    private static int getRandomInteger() {
        Random random = new Random();
        return random.nextInt(7); // 0 ~ 6 사이의 랜던값 추출
    }
}
