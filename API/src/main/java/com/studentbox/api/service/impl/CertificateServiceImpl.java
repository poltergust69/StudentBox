package com.studentbox.api.service.impl;

import com.studentbox.api.entities.certificate.Certificate;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.certificate.CertificateCreationModel;
import com.studentbox.api.models.certificate.CertificateModel;
import com.studentbox.api.repository.CertificateRepository;
import com.studentbox.api.service.CertificateService;
import com.studentbox.api.service.StudentService;
import com.studentbox.api.utils.mappers.CertificateMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.SKILL_CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentService studentService;

    private final static Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);

    @Override
    public Certificate findById(String id) {
        return certificateRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(String.format(SKILL_CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public List<CertificateModel> getCertificates() {
        Student student = studentService.findLoggedInStudent().orElseThrow(NotAuthenticatedException::new);

        List<Certificate> certificates = certificateRepository.findAllByStudent(student);

        return CertificateMapper.mapAllToModel(certificates);
    }

    @Override
    public void addCertificateToStudent(CertificateCreationModel certificateCreationModel) {
        Student student = studentService.findLoggedInStudent().orElseThrow(NotAuthenticatedException::new);

        Certificate certificate = new Certificate(certificateCreationModel, student);

        try{
            certificateRepository.save(certificate);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteCertificate(String id) {
        Certificate certificate = findById(id);

        certificateRepository.delete(certificate);
    }
}
