package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.student.certificate.Certificate;
import com.studentbox.api.models.student.certificate.CertificateModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class CertificateMapper {

    private CertificateMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static CertificateModel mapToModel(Certificate certificate){
        return new CertificateModel(certificate);
    }

    public static List<CertificateModel> mapAllToModel(List<Certificate> certificates){
        return certificates
                .stream()
                .map(CertificateMapper::mapToModel)
                .toList();
    }
}
