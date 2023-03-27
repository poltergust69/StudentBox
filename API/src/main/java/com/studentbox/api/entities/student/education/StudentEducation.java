package com.studentbox.api.entities.student.education;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationModificationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name="student_educations")
@NoArgsConstructor
public class StudentEducation {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "education_id")
    private Education education;

    private String description;

    @Column(name = "started_at")
    private LocalDate startedAt;

    @Column(name = "ended_at")
    private LocalDate endedAt;

    public StudentEducation(EducationCreationModel educationCreationModel, Student student, Education education) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.education = education;
        this.description = educationCreationModel.getDescription();
        this.startedAt = educationCreationModel.getStartedAt();
        this.endedAt = educationCreationModel.getEndedAt();
    }

    public void modifyStudentEducation(EducationModificationModel educationModificationModel){
        this.startedAt = isNull(educationModificationModel.getStartedAt()) ? this.startedAt : educationModificationModel.getStartedAt();
        this.endedAt = isNull(educationModificationModel.getEndedAt()) ? this.endedAt : educationModificationModel.getEndedAt();
        this.description = isNull(educationModificationModel.getDescription()) ? this.description : educationModificationModel.getDescription();
    }
}
