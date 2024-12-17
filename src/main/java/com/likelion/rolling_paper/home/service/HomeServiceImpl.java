package com.likelion.rolling_paper.home.service;

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
    public Long createRollingPaperHome(String uuid, CreateHomeReqDto createHomeReqDto) {
        User user = userRepository.getByUuid(UUID.fromString(uuid));
        RollingPaperHome newHome = rollingPaperHomeRepository.save(RollingPaperHome.toEntity(user, createHomeReqDto));
        return newHome.getId();
    }

    @Override
    public List<GetMyRollingPaperHomeRes> getMyRollingPaperHomes(String uuid, Pageable pageable) {
        User user = userRepository.getByUuid(UUID.fromString(uuid));
        List<RollingPaperHome> myRollingPaperHome = participantRepository.findMyRollingPaperHome(user, pageable);
        List<GetMyRollingPaperHomeRes> responseDtoList = new ArrayList<>();
        for (RollingPaperHome home: myRollingPaperHome) {
            responseDtoList.add(GetMyRollingPaperHomeRes.of(home));
        }
        return responseDtoList;
    }
}
