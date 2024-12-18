package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.paper.dto.CreateMessageReq;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.MessageInfoRes;
import com.likelion.rolling_paper.paper.exception.RollingPaperAlreadyExistException;
import com.likelion.rolling_paper.repository.MessageRepository;
import com.likelion.rolling_paper.repository.RollingPaperRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RollingPaperServiceImpl implements RollingPaperService {
    private final UserRepository userRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public CreateRollingPaperRes createRollingPaper(String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);

        rollingPaperRepository.findByOwner(user)
                .ifPresent(existing -> {
                    log.info("롤링페이퍼 이미 존재함");
                    throw new RollingPaperAlreadyExistException();
                });

        RollingPaper newPaper = rollingPaperRepository.save(RollingPaper.toEntity(user));
        return CreateRollingPaperRes.of(newPaper);
    }

    @Override
    public List<GetRollingPaperListRes> getRollingPaperList() {
        List<RollingPaper> rollingPapers = rollingPaperRepository.findAll();
        return GetRollingPaperListRes.ofList(rollingPapers);
    }

    @Override
    public void getMessageWritingIsAvailable(String kakaoId, Long paperId) {

        // 존재하지 않는 눈덩이로 들어왔을 때 (ROLLING_PAPER_NOT_FOUND_404)

        // 눈덩이 주인이 메시지 작성을 종료했다면 (ROLLING_PAPER_NOT_AVAILABLE_400)

        // 이미 작성한 눈덩이라면 (MESSAGE_DUPLICATED_409)

        // 그게 아니라면 메시지 작성 가능
    }

    @Override
    public MessageInfoRes createNewMessage(CreateMessageReq createMessageReq, String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaper paper = rollingPaperRepository.getById(createMessageReq.paperId());
        int randomEmojiValue = getRandomInteger();
        Message message = messageRepository.save(Message.toEntity(user, createMessageReq.content(), randomEmojiValue, paper));
        return MessageInfoRes.of(message);
    }

    private static int getRandomInteger() {
        Random random = new Random();
        return random.nextInt(7); // 0 ~ 6 사이의 랜던값 추출
    }
}
