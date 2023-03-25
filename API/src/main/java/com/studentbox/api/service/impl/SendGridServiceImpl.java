package com.studentbox.api.service.impl;

import com.studentbox.api.config.SendGridConfig;
import com.studentbox.api.models.sendgrid.ForgotPasswordForm;
import com.studentbox.api.service.SendGridService;
import com.studentbox.api.utils.containers.LoggerMessageContainer;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendGridServiceImpl implements SendGridService {
    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    private final SendGridConfig sendGridConfig;
    private final Logger LOG = LoggerFactory.getLogger(SendGridServiceImpl.class);
    @Override
    public void sendForgotPasswordEmail(String email, String username, String secretKey) {
        ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm(
                email,
                sendGridConfig.getFromEmail(),
                username,
                secretKey,
                sendGridConfig.getTemplateId()
        );

        try{
            HttpEntity<ForgotPasswordForm> request = new HttpEntity<>(forgotPasswordForm, this.httpHeaders);

            ResponseEntity<String> response = restTemplate.postForEntity(sendGridConfig.getApiUrl(), request, String.class);

            LOG.info(String.format(LoggerMessageContainer.SENDGRID_EMAIL_RESULT_MESSAGE, username, response.getStatusCode().is2xxSuccessful() ? Strings.EMPTY : "NOT"));
        }
        catch (Exception ex){
            LOG.error(ex.getMessage());
        }
    }
}
