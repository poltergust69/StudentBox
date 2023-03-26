package com.studentbox.api.models.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailTemplateData {
    private String userUsername;
    private String secretKey;
}
