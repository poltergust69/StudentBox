package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.certificate.Certificate;
import com.studentbox.api.models.certificate.CertificateModel;

import java.util.List;

public class CertificateMapper {

    public static CertificateModel mapToModel(Certificate certificate){
        return new CertificateModel(certificate);
    }

    public static List<CertificateModel> mapAllToModel(List<Certificate> certificates){
        return certificates.stream().map(CertificateMapper::mapToModel).toList();
    }
}
