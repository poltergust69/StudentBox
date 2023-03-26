package com.studentbox.api.models.company;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.models.user.UserDetailsModel;
import lombok.Data;

@Data
public class CompanyDetailsModel extends UserDetailsModel{

    private String name;

    public CompanyDetailsModel(Company company) {
        super(company.getUser());
        this.name = company.getName();
    }
}
