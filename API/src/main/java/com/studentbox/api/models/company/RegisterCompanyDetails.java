package com.studentbox.api.models.company;

import com.studentbox.api.models.user.RegisterUserDetails;
import lombok.Data;

@Data
public class RegisterCompanyDetails extends RegisterUserDetails {
    private String name;

    public RegisterCompanyDetails(String username, String email, String password, String avatarUrl, String name) {
        super(username, email, password, avatarUrl);
        this.name = name;
    }
}
