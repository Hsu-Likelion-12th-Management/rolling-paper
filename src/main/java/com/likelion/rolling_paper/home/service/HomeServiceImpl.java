package com.likelion.rolling_paper.home.service;

import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
import com.likelion.rolling_paper.repository.RollingPaperHomeRepository;
import com.likelion.rolling_paper.repository.UserRepository;
import com.likelion.rolling_paper.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final UserRepository userRepository;
    private final RollingPaperHomeRepository rollingPaperHomeRepository;

    @Override
    @Transactional
    public SuccessResponse<?> createRollingPaperHome(String email, CreateHomeReqDto createHomeReqDto) {
        User user = userRepository.getByEmail(email);
        RollingPaperHome newHome
                = rollingPaperHomeRepository.save(RollingPaperHome.toEntity(user, createHomeReqDto));
        return SuccessResponse.of(newHome.getId());
    }

    @Override
    public SuccessResponse<?> getMyRollingPaperHomes(String email) {
        return null;
    }
}
