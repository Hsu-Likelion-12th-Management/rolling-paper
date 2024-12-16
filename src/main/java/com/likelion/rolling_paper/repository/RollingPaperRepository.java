package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.RollingPaper;
import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.paper.exception.RollingPaperNotFoundException;
import com.likelion.rolling_paper.user.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollingPaperRepository extends JpaRepository<RollingPaper,Long> {
    default RollingPaper getById(Long id) {
        return findById(id).orElseThrow(RollingPaperNotFoundException::new);
    }
}
