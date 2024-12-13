package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.user.exception.UserNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
    Optional<User> findByEmail(String email);
}
