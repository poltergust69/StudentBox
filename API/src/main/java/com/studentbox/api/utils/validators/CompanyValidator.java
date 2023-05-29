package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.SharedMethodContainer.isNameInvalid;

@Component
@AllArgsConstructor
public class CompanyValidator {
    private final UserValidator userValidator;

    public void validateCompanyRegistrationDetails(RegisterCompanyDetails companyDetails){
        userValidator.validateUserRegistrationDetails(companyDetails);

        if(isNameInvalid(companyDetails.getName())){
            throw new NotValidException(NOT_VALID_COMPANY_NAME_EXCEPTION_MESSAGE);
        }
    }


}
