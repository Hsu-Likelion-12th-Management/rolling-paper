package com.likelion.rolling_paper.repository;

import com.likelion.rolling_paper.domain.User;
import com.likelion.rolling_paper.user.exception.UserNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    default User getByUuid(UUID uuid) {
        return findByUuid(uuid).orElseThrow(UserNotFoundException::new);
    }
    Optional<User> findByUuid(UUID uuid);
}
