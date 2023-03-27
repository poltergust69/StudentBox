package com.studentbox.api.entities.user.forgotpassword;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.utils.containers.SharedMethodContainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_forgot_password_requests")
@NoArgsConstructor
public class ForgotPasswordRequest {
    @Id
    @Column(name="user_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="secret_code")
    private String secret;

    @Column(name="created_at")
    private Timestamp createdAt;

    public ForgotPasswordRequest(User user) {
        this.id = user.getId();
        this.user = user;
        this.secret = SharedMethodContainer.generateRandomSecretKey();
        this.createdAt = Timestamp.from(Instant.now());
    }
}
