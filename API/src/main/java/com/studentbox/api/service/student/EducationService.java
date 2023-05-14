package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfoModel;

import java.util.List;

public interface EducationService {

    StudentEducation findById(String id);
    List<EducationInfoModel> getEducationInfo(Student student);
    void addEducationToStudent(Student student, EducationCreationModel educationCreationModel);
    void editStudentEducation(String id, EducationCreationModel educationCreationModel);
    void deleteStudentEducation(String id);
}
