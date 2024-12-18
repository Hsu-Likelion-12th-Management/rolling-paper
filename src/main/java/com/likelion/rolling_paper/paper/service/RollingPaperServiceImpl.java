package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.message.exception.MessageAlreadyExistException;
import com.likelion.rolling_paper.paper.dto.CreateRollingPaperRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperIsFinishRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperListRes;
import com.likelion.rolling_paper.paper.dto.GetRollingPaperMessageListRes;
import com.likelion.rolling_paper.paper.exception.RollingPaperAlreadyExistException;
import com.likelion.rolling_paper.paper.exception.RollingPaperNotAvailableException;
import com.likelion.rolling_paper.paper.exception.RollingPaperOpenUnauthorizedException;
import com.likelion.rolling_paper.paper.exception.RollingPaperUnauthorizedException;
import com.likelion.rolling_paper.repository.MessageRepository;
import com.likelion.rolling_paper.repository.RollingPaperRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.List;
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
        RollingPaper rollingPaper = rollingPaperRepository.getById(paperId);

        // 눈덩이 주인이 메시지 작성을 종료했다면 (ROLLING_PAPER_NOT_AVAILABLE_400)
        if (rollingPaper.getIsFinish()) {
            throw new RollingPaperNotAvailableException();
        }

        // 현재 접속한 사용자 조회
        User user = userRepository.getByKakaoId(kakaoId);

        // 눈덩이 주인 본인은 메시지 작성 불가 (ROLLING_PAPER_UNAUTHORIZED_401)
        if (rollingPaper.getOwner().equals(user)) {
            throw new RollingPaperUnauthorizedException();
        }

        // 이미 작성한 눈덩이라면 (MESSAGE_DUPLICATED_409)
        if (messageRepository.existsByUserAndRollingPaper(user, rollingPaper)) {
            throw new MessageAlreadyExistException();
        }

        // 그게 아니라면 메시지 작성 가능
    }

    @Override
    public GetRollingPaperIsFinishRes getRollingPaperIsFinishRes(String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaper rollingPaper = rollingPaperRepository.getByOwner(user);
        return GetRollingPaperIsFinishRes.of(rollingPaper);
    }

    @Transactional
    @Override
    public void changeRollingPaperStatusToFinish(String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaper rollingPaper = rollingPaperRepository.getByOwner(user);
        rollingPaper.changeRollingPaperStatusToFinish();
    }

    @Override
    public List<GetRollingPaperMessageListRes> getRollingPaperMessageListRes(String kakaoId, Long paperId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaper rollingPaper = rollingPaperRepository.getByOwner(user);

        if (!user.equals(rollingPaperRepository.getById(paperId).getOwner())) {
            throw new RollingPaperOpenUnauthorizedException();
        }

        List<Message> messages = messageRepository.findAllByRollingPaper(rollingPaper);
        return GetRollingPaperMessageListRes.ofList(messages);
    }
}
