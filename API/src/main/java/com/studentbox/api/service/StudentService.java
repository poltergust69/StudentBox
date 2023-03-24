package com.studentbox.api.service;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.certificate.CertificateCreationModel;
import com.studentbox.api.models.certificate.CertificateModel;
import com.studentbox.api.models.education.EducationCreationModel;
import com.studentbox.api.models.education.EducationInfo;
import com.studentbox.api.models.education.EducationModificationModel;
import com.studentbox.api.models.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.skill.SkillModel;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> findLoggedInStudent();
    List<SkillModel> getSkillsForStudent(Student student);
    void addSkillToStudent(String skillId);
    void deleteStudentSkill(String skillId);
    List<EducationInfo> getEducationInfo();
    void addEducationToStudent(EducationCreationModel educationCreationModel);
    void editStudentEducation(String id, EducationModificationModel educationModificationModel);
    void deleteStudentEducation(String id);
    List<CertificateModel> getCertificates();
    void addCertificateToStudent(CertificateCreationModel certificateCreationModel);
    void deleteCertificate(String id);
    void addEmploymentInfoToStudent(EmploymentInfoCreationModel employmentInfoCreationModel);
    void deleteEmploymentInfo(String id);
}
