package com.studentbox.api.models.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MailPersonalizations {
    private List<MailAccount> to;
    private MailTemplateData dynamic_template_data;
}

