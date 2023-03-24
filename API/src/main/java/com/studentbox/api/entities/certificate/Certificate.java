package com.studentbox.api.entities.certificate;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.certificate.CertificateCreationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name="student_certificates")
@NoArgsConstructor
public class Certificate {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String issuedBy;

    private String description;

    @Column(length = 250)
    private String documentUrl;

    private LocalDate issuedAt;

    public Certificate(CertificateCreationModel certificateCreationModel, Student student) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.name = certificateCreationModel.getName();
        this.issuedBy = certificateCreationModel.getIssuedBy();
        this.description = certificateCreationModel.getDescription();
        this.documentUrl = certificateCreationModel.getDocumentUrl();
        this.issuedAt = certificateCreationModel.getIssuedAt();
    }
}
