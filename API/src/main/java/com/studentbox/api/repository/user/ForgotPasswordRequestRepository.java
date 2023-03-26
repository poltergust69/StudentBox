package com.studentbox.api.repository.user;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.forgotpassword.ForgotPasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForgotPasswordRequestRepository extends JpaRepository<ForgotPasswordRequest, UUID> {
    public Optional<ForgotPasswordRequest> findByUser(User user);
}
