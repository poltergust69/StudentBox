package com.studentbox.api.repository.student;

import com.studentbox.api.entities.student.certificate.Certificate;
import com.studentbox.api.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, UUID> {

    List<Certificate> findAllByStudent(Student student);
}
