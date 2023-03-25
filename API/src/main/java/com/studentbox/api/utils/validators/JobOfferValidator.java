package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.joboffer.JobOfferCreationModel;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class JobOfferValidator {
    private JobOfferValidator() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static void validateJobOffer(JobOfferCreationModel jobOfferCreationModel){
        if(!isDescriptionValid(jobOfferCreationModel.getDescription())){
            throw new NotValidException(NOT_VALID_JOB_OFFER_DESCRIPTION_EXCEPTION_MESSAGE);
        }
        if(!isSalaryValid(jobOfferCreationModel.getSalary())){
            throw new NotValidException(NOT_VALID_JOB_OFFER_SALARY_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isDescriptionValid(String description){
        return !isNull(description) && !description.isBlank();
    }

    private static boolean isSalaryValid(Long salary){
        return !isNull(salary) && (salary > 0);
    }
}
