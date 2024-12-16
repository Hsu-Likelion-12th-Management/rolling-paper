package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.Message;
import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.user.exception.UserNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
