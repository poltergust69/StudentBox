package com.studentbox.api.service;


public interface SendGridService {
    void sendForgotPasswordEmail(String email, String username, String secretKey);
}
