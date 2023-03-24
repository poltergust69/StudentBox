package com.studentbox.api.service;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.models.company.RegisterCompanyDetails;

public interface CompanyService {
    void registerCompany(RegisterCompanyDetails details);
    Company findByName(String name);
}
