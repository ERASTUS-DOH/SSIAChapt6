package com.inspired.springSecInActionChapter6.repositories;

import com.inspired.springSecInActionChapter6.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
