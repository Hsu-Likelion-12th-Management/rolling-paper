package com.likelion.rolling_paper.home.service;

import com.likelion.rolling_paper.domain.Participant;
import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.home.dto.GetMyRollingPaperHomeRes;
import com.likelion.rolling_paper.repository.ParticipantRepository;
import com.likelion.rolling_paper.repository.RollingPaperHomeRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final RollingPaperHomeRepository rollingPaperHomeRepository;

    @Override
    @Transactional
    public Long createRollingPaperHome(String kakaoId, CreateHomeReqDto createHomeReqDto) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaperHome newHome = rollingPaperHomeRepository.save(RollingPaperHome.toEntity(user, createHomeReqDto));
        participantRepository.save(Participant.toEntity(user, newHome)); // 참여자 추가
        return newHome.getId();
    }

    @Override
    public List<GetMyRollingPaperHomeRes> getMyRollingPaperHomes(String kakaoId, Pageable pageable) {
        User user = userRepository.getByKakaoId(kakaoId);
        List<RollingPaperHome> myRollingPaperHome = participantRepository.findMyRollingPaperHome(user, pageable);
        List<GetMyRollingPaperHomeRes> responseDtoList = new ArrayList<>();
        for (RollingPaperHome home: myRollingPaperHome) {
            responseDtoList.add(GetMyRollingPaperHomeRes.of(home));
        }
        return responseDtoList;
    }
}
