package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.Participant;
import com.likelion.rolling_paper.domain.RollingPaperHome;
import com.likelion.rolling_paper.domain.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    @Query("SELECT p.home FROM Participant p WHERE p.user = :user")
    List<RollingPaperHome> findMyRollingPaperHome(User user, Pageable pageable);
}
