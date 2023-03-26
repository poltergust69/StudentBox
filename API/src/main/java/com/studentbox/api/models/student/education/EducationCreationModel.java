package com.studentbox.api.models.student.education;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationCreationModel {

    private String schoolName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String description;
}
