package com.studentbox.api.entities.employ;


import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="match_student_job_offer")
@NoArgsConstructor
public class StudentJobOffer {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "job_id")
    private JobOffer jobOffer;

    @Column(name = "student_status")
    private char studentStatus;


    @Column(name = "company_status")
    private char companyStatus;

    @Column(name = "score")
    private double score;

    public StudentJobOffer(Student student, JobOffer jobOffer, double score) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.jobOffer = jobOffer;
        this.studentStatus = 'W';
        this.companyStatus = 'W';
        this.score = score;
    }
}
