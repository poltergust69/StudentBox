package com.studentbox.api.entities.employ;


import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="job_offers_students_companies")
@NoArgsConstructor
public class JobOfferStatus {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "job_id")
    private JobOffer jobOffer;

    @Column(name = "student_status")
    private AcceptanceStatus studentStatus;

    @Column(name = "company_status")
    private AcceptanceStatus companyStatus;

    public JobOfferStatus(Student student, JobOffer jobOffer) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.jobOffer = jobOffer;
        this.studentStatus = AcceptanceStatus.WAITING;
        this.companyStatus = AcceptanceStatus.WAITING;
    }
}
