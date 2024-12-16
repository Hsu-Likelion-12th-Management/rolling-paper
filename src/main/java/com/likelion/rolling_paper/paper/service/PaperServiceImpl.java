package com.likelion.rolling_paper.paper.service;

import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.domain.State;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.paper.dto.RollingPaperInfoRes;
import com.likelion.rolling_paper.repository.RollingPaperHomeRepository;
import com.likelion.rolling_paper.repository.RollingPaperRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService{
    private final UserRepository userRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperHomeRepository rollingPaperHomeRepository;
    @Override
    @Transactional
    public RollingPaperInfoRes createRollingPaperPage(Long homeId, String kakaoId) {
        User user = userRepository.getByKakaoId(kakaoId);
        RollingPaperHome paperHome = rollingPaperHomeRepository.getById(homeId);
        RollingPaper newPaper = rollingPaperRepository.save(RollingPaper.toEntity(user.getName(), paperHome));
        return RollingPaperInfoRes.of(newPaper);
    }
}
