package com.studentbox.api.models.employment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmploymentInfoCreationModel {

    private String companyName;
    private String jobPosition;
    private LocalDate startedAt;
    private LocalDate endedAt;
}
