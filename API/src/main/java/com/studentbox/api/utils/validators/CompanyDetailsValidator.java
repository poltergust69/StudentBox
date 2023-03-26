package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.company.RegisterCompanyDetails;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class CompanyDetailsValidator {
    private CompanyDetailsValidator(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static void validateCompanyDetails(RegisterCompanyDetails companyDetails){
        UserDetailsValidator.validateUserDetails(companyDetails);

        if(isNameInvalid(companyDetails.getName())){
            throw new NotValidException(NOT_VALID_COMPANY_NAME_EXCEPTION_MESSAGE);
        }
    }

    public static boolean isNameInvalid(String name){
        return !isNull(name) && !name.isBlank();
    }
}
