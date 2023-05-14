package com.studentbox.api.models.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.studentbox.api.utils.containers.ConstantsContainer.PLACEHOLDER_AVATAR_URL;
import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class RegisterUserDetails {
    private String username;
    private String email;
    private String password;
    private String avatarUrl;

    public RegisterUserDetails(String username, String email, String password, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatarUrl = (isNull(avatarUrl) || avatarUrl.isBlank()) ? PLACEHOLDER_AVATAR_URL : avatarUrl;
    }
}