package com.studentbox.api.service.impl;

import com.studentbox.api.entities.skill.Skill;
import com.studentbox.api.entities.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserService userService;
    private final StudentRepository studentRepository;
    private final StudentSkillService studentSkillService;
    private final EducationService educationService;
    private final CertificateService certificateService;
    private final EmploymentInfoService employmentInfoService;

    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Optional<Student> findLoggedInStudent() {
        User user = userService.findAuthenticatedUser();
        return studentRepository.findById(user.getId());
    }

    @Override
    public List<SkillModel> getSkillsForStudent(Student student) {

        List<Skill> skills = new ArrayList<>();
        List<StudentSkill> studentSkills = studentSkillService.findAllByStudent(student);

        studentSkills.forEach(studentSkill -> skills.add(studentSkill.getSkill()));

        return SkillMapper.mapAllToModel(skills);
    }

    @Override
    public void addSkillToStudent(String skillId) {
        studentSkillService.addSkillToStudent(skillId);
    }

    @Override
    public void deleteStudentSkill(String skillId) {
        studentSkillService.deleteStudentSkill(skillId);
    }

    @Override
    public List<EducationInfo> getEducationInfo() {
        return educationService.getEducationInfo();
    }

    @Override
    public void addEducationToStudent(EducationCreationModel educationCreationModel) {
        educationService.addEducationToStudent(educationCreationModel);
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
        return certificateService.getCertificates();
    }

    @Override
    public void addCertificateToStudent(CertificateCreationModel certificateCreationModel) {
        certificateService.addCertificateToStudent(certificateCreationModel);
    }

    @Override
    public void deleteCertificate(String id) {
        certificateService.deleteCertificate(id);
    }

    @Override
    public void addEmploymentInfoToStudent(EmploymentInfoCreationModel employmentInfoCreationModel) {
        employmentInfoService.addEmploymentInfoToStudent(employmentInfoCreationModel);
    }

    @Override
    public void deleteEmploymentInfo(String id) {
        employmentInfoService.deleteEmploymentInfo(id);
    }


}
