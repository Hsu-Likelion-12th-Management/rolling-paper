package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.user.exception.UserNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollingPaperHomeRepository extends JpaRepository<RollingPaperHome,Long> {
    default RollingPaperHome getById(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }
}
