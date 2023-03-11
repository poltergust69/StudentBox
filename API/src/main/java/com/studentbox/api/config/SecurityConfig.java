package com.studentbox.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

@Getter
@Setter
@Configuration
public class SecurityConfig {
    @Value("${security.jwt.header}")
    private String jwtHeader;
    private String jwtSecretKey;
    private String jwtRefreshSecretKey;
    @Value("${security.jwt.prefix}")
    private String jwtPrefix;

    public SecurityConfig(){
        this.jwtSecretKey = UUID.randomUUID().toString();
        this.jwtRefreshSecretKey = UUID.randomUUID().toString();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
