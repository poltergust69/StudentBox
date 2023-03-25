package com.studentbox.api.service.impl;

import com.studentbox.api.entities.skill.Skill;
import com.studentbox.api.entities.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.certificate.CertificateCreationModel;
import com.studentbox.api.models.certificate.CertificateModel;
import com.studentbox.api.models.education.EducationCreationModel;
import com.studentbox.api.models.education.EducationInfo;
import com.studentbox.api.models.education.EducationModificationModel;
import com.studentbox.api.models.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.skill.SkillModel;
import com.studentbox.api.repository.StudentRepository;
import com.studentbox.api.service.*;
import com.studentbox.api.utils.mappers.SkillMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.STUDENT_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserService userService;
    private final StudentRepository studentRepository;
    private final StudentSkillService studentSkillService;
    private final EducationService educationService;
    private final CertificateService certificateService;
    private final EmploymentInfoService employmentInfoService;

    @Override
    public Student findLoggedInStudent() {
        User user = userService.findAuthenticatedUser();
        return studentRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException(String.format(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE, user.getUsername())));
    }

    @Override
    public List<SkillModel> getSkillsForStudent() {
        Student student = findLoggedInStudent();
        List<Skill> skills = new ArrayList<>();
        List<StudentSkill> studentSkills = studentSkillService.findAllByStudent(student);

        studentSkills.forEach(studentSkill -> skills.add(studentSkill.getSkill()));

        return SkillMapper.mapAllToModel(skills);
    }

    @Override
    public void addSkillToStudent(String skillId) {
        Student student = findLoggedInStudent();
        studentSkillService.addSkillToStudent(student, skillId);
    }

    @Override
    public void deleteStudentSkill(String skillId) {
        Student student = findLoggedInStudent();
        studentSkillService.deleteStudentSkill(student, skillId);
    }

    @Override
    public List<EducationInfo> getEducationInfo() {
        Student student = findLoggedInStudent();
        return educationService.getEducationInfo(student);
    }

    @Override
    public void addEducationToStudent(EducationCreationModel educationCreationModel) {
        Student student = findLoggedInStudent();
        educationService.addEducationToStudent(student, educationCreationModel);
    }

    @Override
    public void editStudentEducation(String id, EducationModificationModel educationModificationModel) {
        educationService.editStudentEducation(id, educationModificationModel);
    }

    @Override
    public void deleteStudentEducation(String id) {
        educationService.deleteStudentEducation(id);
    }

    @Override
    public List<CertificateModel> getCertificates() {
        Student student = findLoggedInStudent();
        return certificateService.getCertificates(student);
    }

    @Override
    public void addCertificateToStudent(CertificateCreationModel certificateCreationModel) {
        Student student = findLoggedInStudent();
        certificateService.addCertificateToStudent(student, certificateCreationModel);
    }

    @Override
    public void deleteCertificate(String id) {
        certificateService.deleteCertificate(id);
    }

    @Override
    public void addEmploymentInfoToStudent(EmploymentInfoCreationModel employmentInfoCreationModel) {
        Student student = findLoggedInStudent();
        employmentInfoService.addEmploymentInfoToStudent(student, employmentInfoCreationModel);
    }

    @Override
    public void deleteEmploymentInfo(String id) {
        employmentInfoService.deleteEmploymentInfo(id);
    }


}
