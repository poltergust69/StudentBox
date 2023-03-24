package com.studentbox.api.models.joboffer;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.jobposition.JobPosition;
import lombok.Data;

@Data
public class JobOfferCreationModel {

    private Company company;
    private JobPosition jobPosition;
    private Long salary;
    private String description;
}
