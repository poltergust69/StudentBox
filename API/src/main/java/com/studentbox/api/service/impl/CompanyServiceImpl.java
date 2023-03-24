package com.studentbox.api.service.impl;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.repository.CompanyRepository;
import com.studentbox.api.service.CompanyService;
import com.studentbox.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.studentbox.api.utils.validators.CompanyDetailsValidator.validateCompanyDetails;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Override
    public void registerCompany(RegisterCompanyDetails details) {
        validateCompanyDetails(details);

        User user = userService.registerUser(details, RoleType.valueOf("COMPANY"));

        Company company = new Company();
        company.setUser(user);
        company.setName(details.getName());

        companyRepository.save(company);
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }
}
