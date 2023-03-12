package com.studentbox.api.repository;

import com.studentbox.api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsernameOrEmail(String username, String email);
    Optional<User> findUserByUsername(String username);
}
