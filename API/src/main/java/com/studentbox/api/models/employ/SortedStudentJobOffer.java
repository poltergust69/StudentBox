package com.studentbox.api.models.employ;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SortedStudentJobOffer implements Comparable<SortedStudentJobOffer> {

    private Double score;

    private JobOffer jobOffer;

    private Student student;

    @Override
    public int compareTo(SortedStudentJobOffer o) {
        return - this.score.compareTo(o.getScore());
    }
}
