package com.studentbox.api.models.company.joboffer;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.jobposition.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class JobOfferModel {
    private UUID id;
    private JobPosition jobPosition;
    private Company company;
    private String description;
    private Long salary;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public JobOfferModel(JobOffer jobOffer) {
        this.id = UUID.randomUUID();
        this.jobPosition = jobOffer.getJobPosition();
        this.company = jobOffer.getCompany();
        this.description = jobOffer.getDescription();
        this.salary = jobOffer.getSalary();
        this.createdAt = jobOffer.getCreatedAt().toLocalDateTime();
        this.modifiedAt = jobOffer.getModifiedAt().toLocalDateTime();
    }
}
