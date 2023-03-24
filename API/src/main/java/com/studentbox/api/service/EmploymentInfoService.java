package com.studentbox.api.service;

import com.studentbox.api.entities.employmentInfo.EmploymentInfo;
import com.studentbox.api.models.employment.EmploymentInfoCreationModel;

public interface EmploymentInfoService {

    EmploymentInfo findById(String id);
    void addEmploymentInfoToStudent(EmploymentInfoCreationModel employmentInfoCreationModel);
    void deleteEmploymentInfo(String id);
}
