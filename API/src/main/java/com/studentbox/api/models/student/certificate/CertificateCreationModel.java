package com.studentbox.api.models.student.certificate;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificateCreationModel {

    private String issuedBy;
    private LocalDate issuedAt;
    private String description;
    private String documentUrl;
    private String name;

}
