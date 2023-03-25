package com.studentbox.api.models.sendgrid;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ForgotPasswordForm {
    private MailAccount from;
    private List<MailPersonalizations> personalizations;
    private String template_id;

    public ForgotPasswordForm(String from, String to, String userUsername, String secretKey, String templateId){

        MailAccount fromAccount = new MailAccount(from);
        MailAccount toAccount = new MailAccount(to);
        MailTemplateData templateData = new MailTemplateData(userUsername, secretKey);
        MailPersonalizations mailPersonalization = new MailPersonalizations(Collections.singletonList(toAccount), templateData);

        this.from = fromAccount;
        this.personalizations = Collections.singletonList(mailPersonalization);
        this.template_id = templateId;
    }
}

