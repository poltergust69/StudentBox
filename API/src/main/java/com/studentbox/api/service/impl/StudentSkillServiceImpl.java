package com.studentbox.api.service.impl;

import com.studentbox.api.entities.skill.Skill;
import com.studentbox.api.entities.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.repository.SkillRepository;
import com.studentbox.api.repository.StudentSkillRepository;
import com.studentbox.api.service.StudentSkillService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.SKILL_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.STUDENT_HAS_NO_SKILL_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class StudentSkillServiceImpl implements StudentSkillService {

    private final StudentSkillRepository studentSkillRepository;
    private final SkillRepository skillRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentSkillServiceImpl.class);

    @Override
    public List<StudentSkill> findAllByStudent(Student student){
        return studentSkillRepository.findAllByStudent(student);
    }

    @Override
    public void addSkillToStudent(Student student, String skillId) {
        Skill skill = skillRepository.findById(UUID.fromString(skillId)).orElseThrow(() -> new NotFoundException(String.format(SKILL_NOT_FOUND_EXCEPTION_MESSAGE, skillId)));

        StudentSkill studentSkill = new StudentSkill(skill, student);

        try{
            studentSkillRepository.save(studentSkill);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteStudentSkill(Student student, String skillId) {
        UUID skillUUID = UUID.fromString(skillId);
        Skill skill = skillRepository.findById(skillUUID)
                .orElseThrow(() -> new NotFoundException(String.format(SKILL_NOT_FOUND_EXCEPTION_MESSAGE, skillId)));
        StudentSkill studentSkill = studentSkillRepository.findByStudentAndSkill(student, skill)
                .orElseThrow(() -> new NotFoundException(String.format(STUDENT_HAS_NO_SKILL_EXCEPTION_MESSAGE, student.getFullName(), skill.getName())));

        studentSkillRepository.delete(studentSkill);
    }

}
