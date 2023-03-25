package com.studentbox.api.service;

import com.studentbox.api.entities.certificate.Certificate;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.certificate.CertificateCreationModel;
import com.studentbox.api.models.certificate.CertificateModel;

import java.util.List;

public interface CertificateService {

    Certificate findById(String id);
    List<CertificateModel> getCertificates(Student student);
    void addCertificateToStudent(Student student, CertificateCreationModel certificateCreationModel);
    void deleteCertificate(String id);
}
