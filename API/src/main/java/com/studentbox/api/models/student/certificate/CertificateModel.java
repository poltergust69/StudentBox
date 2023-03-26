package com.studentbox.api.models.student.certificate;

import com.studentbox.api.entities.student.certificate.Certificate;
import com.studentbox.api.entities.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CertificateModel {

    private UUID id;
    private Student student;
    private String name;
    private String issuedBy;
    private String description;
    private String documentUrl;
    private LocalDate issuedAt;

    public CertificateModel(Certificate certificate){
        this.id = certificate.getId();
        this.student = certificate.getStudent();
        this.name = certificate.getName();
        this.issuedBy = certificate.getIssuedBy();
        this.description = certificate.getDescription();
        this.documentUrl = certificate.getDocumentUrl();
        this.issuedAt = certificate.getIssuedAt();
    }
}
