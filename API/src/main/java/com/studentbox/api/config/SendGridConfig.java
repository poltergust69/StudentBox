package com.studentbox.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Getter
@Configuration
public class SendGridConfig {
    @Value("${spring.sendgrid.api_key}")
    private String sendGridApiKey;
    @Value("${spring.sendgrid.template-id}")
    private String templateId;
    @Value("${spring.sendgrid.from}")
    private String fromEmail;
    @Value("${spring.sendgrid.url}")
    private String apiUrl;

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + getSendGridApiKey());

        return httpHeaders;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
