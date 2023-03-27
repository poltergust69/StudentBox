package com.studentbox.api.models.student.education;

import com.studentbox.api.entities.student.education.Education;
import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.entities.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EducationInfo {

    private UUID id;
    private Student student;
    private Education education;
    private String description;
    private LocalDate startedAt;
    private LocalDate endedAt;

    public EducationInfo(StudentEducation studentEducation) {
        this.id = studentEducation.getId();
        this.student = studentEducation.getStudent();
        this.education = studentEducation.getEducation();
        this.description = studentEducation.getDescription();
        this.startedAt = studentEducation.getStartedAt();
        this.endedAt = studentEducation.getEndedAt();
    }
}
