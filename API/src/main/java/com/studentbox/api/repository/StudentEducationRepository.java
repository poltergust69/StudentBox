package com.studentbox.api.repository;

import com.studentbox.api.entities.education.StudentEducation;
import com.studentbox.api.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentEducationRepository extends JpaRepository<StudentEducation, UUID> {

    List<StudentEducation> findAllByStudent(Student student);

}
