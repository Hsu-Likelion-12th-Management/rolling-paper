package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
