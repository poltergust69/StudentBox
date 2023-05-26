package com.studentbox.api.service.student.impl;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.entities.student.skill.StudentSkill;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.student.RegisterStudentDetails;
import com.studentbox.api.models.student.certificate.CertificateCreationModel;
import com.studentbox.api.models.student.certificate.CertificateModel;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfoModel;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.student.skill.SkillModel;
import com.studentbox.api.repository.student.StudentRepository;
import com.studentbox.api.service.student.*;
import com.studentbox.api.service.user.UserService;
import com.studentbox.api.utils.mappers.SkillMapper;
import com.studentbox.api.utils.validators.StudentValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private final StudentValidator studentValidator;

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
    public List<EducationInfoModel> getEducationInfo() {
        Student student = findLoggedInStudent();
        return educationService.getEducationInfo(student);
    }

    @Override
    public void addEducationToStudent(EducationCreationModel educationCreationModel) {
        Student student = findLoggedInStudent();
        educationService.addEducationToStudent(student, educationCreationModel);
    }

    @Override
    public void editStudentEducation(String id, EducationCreationModel educationCreationModel) {
        educationService.editStudentEducation(id, educationCreationModel);
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

    @Override
    public void registerStudent(RegisterStudentDetails details) {
        studentValidator.validateStudentRegistrationDetails(details);

        User user = userService.registerUser(details, RoleType.STUDENT);

        Student student = new Student();

        student.setId(UUID.randomUUID());
        student.setUser(user);
        student.setFirstName(details.getFirstName());
        student.setLastName(details.getLastName());
        student.setDateOfBirth(LocalDate.parse(details.getDateOfBirth()));
        student.setDescription(details.getDescription());

        studentRepository.save(student);
    }
}
