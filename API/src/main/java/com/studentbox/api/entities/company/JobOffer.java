package com.studentbox.api.entities.company;

import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.models.joboffer.JobOfferCreationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name="job_offers")
@NoArgsConstructor
public class JobOffer {

    @Id
    private UUID id;

    @JoinColumn(name="company_id")
    @ManyToOne
    private Company company;

    @JoinColumn(name="position_id")
    @ManyToOne
    private JobPosition jobPosition;

    private String description;

    @JoinColumn(name="monthly_salary_in_euros")
    private Long salary;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="modified_at")
    private Timestamp modifiedAt;

    public JobOffer(JobOfferCreationModel jobOfferCreationModel) {
        this.id = UUID.randomUUID();
        this.company = jobOfferCreationModel.getCompany();
        this.jobPosition = jobOfferCreationModel.getJobPosition();
        this.description = jobOfferCreationModel.getDescription();
        this.salary = jobOfferCreationModel.getSalary();
        this.modifiedAt = Timestamp.from(Instant.now());
        this.createdAt = Timestamp.from(Instant.now());
    }
}
