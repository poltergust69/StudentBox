package com.studentbox.api.service;

import com.studentbox.api.entities.employmentinfo.EmploymentInfo;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.employment.EmploymentInfoCreationModel;

public interface EmploymentInfoService {

    EmploymentInfo findById(String id);
    void addEmploymentInfoToStudent(Student student, EmploymentInfoCreationModel employmentInfoCreationModel);
    void deleteEmploymentInfo(String id);
}
