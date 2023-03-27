package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;

import java.util.List;

public interface StudentSkillService {

    List<StudentSkill> findAllByStudent(Student student);
    void addSkillToStudent(Student student, String skillId);
    void deleteStudentSkill(Student student, String skillId);
}
