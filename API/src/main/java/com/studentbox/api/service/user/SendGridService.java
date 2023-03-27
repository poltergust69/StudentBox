package com.studentbox.api.service.user;


public interface SendGridService {
    void sendForgotPasswordEmail(String email, String username, String secretKey);
}
