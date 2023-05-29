package com.studentbox.api.models.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordModel {
    private String secretKey;
    private String email;
    private String password;
}
