package com.studentbox.api.models.student.employment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmploymentInfoModel {
    private UUID id;
    private String companyName;
    private String jobPosition;
    private LocalDate startedAt;
    private LocalDate endedAt;
}
