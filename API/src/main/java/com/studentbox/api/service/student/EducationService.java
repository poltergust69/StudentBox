package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfo;
import com.studentbox.api.models.student.education.EducationModificationModel;

import java.util.List;

public interface EducationService {

    StudentEducation findById(String id);
    List<EducationInfo> getEducationInfo(Student student);
    void addEducationToStudent(Student student, EducationCreationModel educationCreationModel);
    void editStudentEducation(String id, EducationModificationModel educationModificationModel);
    void deleteStudentEducation(String id);
}
