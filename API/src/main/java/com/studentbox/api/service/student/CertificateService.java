package com.studentbox.api.service.student;

import com.studentbox.api.entities.student.certificate.Certificate;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.certificate.CertificateCreationModel;
import com.studentbox.api.models.student.certificate.CertificateModel;

import java.util.List;

public interface CertificateService {

    Certificate findById(String id);
    List<CertificateModel> getCertificates(Student student);
    void addCertificateToStudent(Student student, CertificateCreationModel certificateCreationModel);
    void deleteCertificate(String id);
}
