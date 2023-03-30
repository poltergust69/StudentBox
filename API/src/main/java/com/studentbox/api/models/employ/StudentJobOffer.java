package com.studentbox.api.models.employ;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentJobOffer {

    private UUID id;

    private Student student;

    private JobOffer jobOffer;


}
