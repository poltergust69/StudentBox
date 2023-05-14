package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.models.company.CompanyDetailsModel;
import com.studentbox.api.models.company.CompanyModel;
import com.studentbox.api.models.user.UserDetailsModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class CompanyMapper {
    private CompanyMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static CompanyModel mapToModel(Company company){
        CompanyModel model = new CompanyModel();

        model.setId(company.getId());
        model.setName(company.getName());
        model.setUsername(company.getUser().getUsername());
        model.setAvatarUrl(company.getUser().getAvatarUrl());

        return model;
    }

    public static List<CompanyModel> mapAllToModel(List<Company> companies){
        return companies
                .stream()
                .map(CompanyMapper::mapToModel)
                .toList();
    }

    public static CompanyDetailsModel mapToDetailsModel(Company company){
        UserDetailsModel userDetailsModel = UserMapper.mapToDetailsModel(company.getUser());
        CompanyDetailsModel companyDetailsModel = new CompanyDetailsModel(userDetailsModel);

        companyDetailsModel.setName(company.getName());

        return companyDetailsModel;
    }
}
