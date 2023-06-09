package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.models.student.RegisterStudentDetails;
import com.studentbox.api.models.student.certificate.CertificateCreationModel;
import com.studentbox.api.models.student.certificate.CertificateModel;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfoModel;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.student.skill.SkillModel;
import com.studentbox.api.models.user.RegisterUserDetails;

import java.util.List;

public interface StudentService {
    Student findLoggedInStudent();
    List<SkillModel> getSkillsForStudent();
    void addSkillToStudent(String skillId);
    void deleteStudentSkill(String skillId);
    List<EducationInfoModel> getEducationInfo();
    void addEducationToStudent(EducationCreationModel educationCreationModel);
    void editStudentEducation(String id, EducationCreationModel educationCreationModel);
    void deleteStudentEducation(String id);
    List<CertificateModel> getCertificates();
    void addCertificateToStudent(CertificateCreationModel certificateCreationModel);
    void deleteCertificate(String id);
    void addEmploymentInfoToStudent(EmploymentInfoCreationModel employmentInfoCreationModel);
    void deleteEmploymentInfo(String id);
    void registerStudent(RegisterStudentDetails details);
}
