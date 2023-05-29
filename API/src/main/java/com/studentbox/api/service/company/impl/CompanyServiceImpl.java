package com.studentbox.api.service.company.impl;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.repository.company.CompanyRepository;
import com.studentbox.api.service.company.CompanyService;
import com.studentbox.api.service.user.UserService;
import com.studentbox.api.utils.validators.CompanyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final CompanyValidator companyValidator;

    @Override
    public void registerCompany(RegisterCompanyDetails details) {
        companyValidator.validateCompanyRegistrationDetails(details);

        User user = userService.registerUser(details, RoleType.COMPANY);

        Company company = new Company();

        company.setId(UUID.randomUUID());
        company.setUser(user);
        company.setName(details.getName());

        companyRepository.save(company);
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }
}
