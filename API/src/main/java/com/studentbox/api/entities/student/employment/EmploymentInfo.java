package com.studentbox.api.entities.student.employment;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name="employment_infos")
@NoArgsConstructor
public class EmploymentInfo {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private UUID companyId;

    private String companyName;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private JobPosition jobPosition;

    private LocalDate startedAt;

    private LocalDate endedAt;

    public EmploymentInfo(EmploymentInfoCreationModel employmentInfoCreationModel, Student student, JobPosition jobPosition, Company company) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.companyId = company.getUser().getId();
        this.companyName = company.getName();
        this.jobPosition = jobPosition;
        this.startedAt = employmentInfoCreationModel.getStartedAt();
        this.endedAt = isNull(employmentInfoCreationModel.getEndedAt()) ? null : employmentInfoCreationModel.getEndedAt();
    }
}
