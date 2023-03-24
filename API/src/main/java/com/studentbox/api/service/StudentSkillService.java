package com.studentbox.api.service;

import com.studentbox.api.entities.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;

import java.util.List;

public interface StudentSkillService {

    List<StudentSkill> findAllByStudent(Student student);
    void addSkillToStudent(String skillId);
    void deleteStudentSkill(String skillId);
}
