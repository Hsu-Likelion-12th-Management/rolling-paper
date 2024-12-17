package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.repository.MessageRepository;
import com.likelion.rolling_paper.repository.RollingPaperHomeRepository;
import com.likelion.rolling_paper.repository.RollingPaperRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService{
    private final UserRepository userRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperHomeRepository rollingPaperHomeRepository;
    private final MessageRepository messageRepository;
    @Override
    @Transactional
    public RollingPaperInfoRes createRollingPaperPage(Long homeId, String uuid) {
        User user = userRepository.getByKakaoId(UUID.fromString(uuid));
        RollingPaperHome paperHome = rollingPaperHomeRepository.getById(homeId);
        RollingPaper newPaper = rollingPaperRepository.save(RollingPaper.toEntity(user.getNickname(), paperHome));
        return RollingPaperInfoRes.of(newPaper);
    }

    @Override
    public MessageInfoRes createNewMessage(CreateMessageReq createMessageReq, String uuid) {
        User user = userRepository.getByKakaoId(UUID.fromString(uuid));
        RollingPaper paper = rollingPaperRepository.getById(createMessageReq.paperId());
        Message message = messageRepository.save(Message.toEntity(user, createMessageReq.content(), paper));
        return MessageInfoRes.of(message);
    }
}
