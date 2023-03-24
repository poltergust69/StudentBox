package com.studentbox.api.service;

import com.studentbox.api.entities.education.StudentEducation;
import com.studentbox.api.models.education.EducationCreationModel;
import com.studentbox.api.models.education.EducationInfo;
import com.studentbox.api.models.education.EducationModificationModel;

import java.util.List;

public interface EducationService {

    StudentEducation findById(String id);
    List<EducationInfo> getEducationInfo();
    void addEducationToStudent(EducationCreationModel educationCreationModel);
    void editStudentEducation(String id, EducationModificationModel educationModificationModel);
    void deleteStudentEducation(String id);
}
