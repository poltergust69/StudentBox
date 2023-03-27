package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;

public interface EmploymentInfoService {

    EmploymentInfo findById(String id);
    void addEmploymentInfoToStudent(Student student, EmploymentInfoCreationModel employmentInfoCreationModel);
    void deleteEmploymentInfo(String id);
}
