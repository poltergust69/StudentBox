package com.studentbox.api.service.impl;

import com.studentbox.api.repository.UserRepository;
import com.studentbox.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
