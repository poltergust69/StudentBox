package com.studentbox.api.repository.student;

import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.entities.student.skill.StudentSkill;
import com.studentbox.api.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentSkillRepository extends JpaRepository<StudentSkill, UUID> {
    List<StudentSkill> findAllByStudent(Student student);
    Optional<StudentSkill> findByStudentAndSkill(Student student, Skill skill);
}
