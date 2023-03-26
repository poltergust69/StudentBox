package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.student.education.EducationCreationModel;

import java.time.LocalDate;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class StudentEducationValidator {
    private StudentEducationValidator() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static void validateStudentEducation(EducationCreationModel educationCreationModel) {
        if(!isSchoolNameValid(educationCreationModel.getSchoolName())){
            throw new NotValidException(NOT_VALID_SCHOOL_NAME_EXCEPTION_MESSAGE);
        }
        if(!isDescriptionValid(educationCreationModel.getDescription())){
            throw new NotValidException(NOT_VALID_EDUCATION_DESCRIPTION_EXCEPTION_MESSAGE);
        }
        if(!areDatesValid(educationCreationModel.getStartedAt(), educationCreationModel.getEndedAt())){
            throw new NotValidException(NOT_VALID_DATES_EXCEPTION_MESSAGE);
        }
    }

    public static boolean areDatesValid(LocalDate startDate, LocalDate endDate) {

        if(startDate == null)
            return false;
        else if (startDate.isAfter(endDate))
            return false;
        else if (startDate.isAfter(LocalDate.now()))
            return false;
        else return !endDate.isAfter(LocalDate.now());
    }

    private static boolean isDescriptionValid(String description){
        return !isNull(description) && !description.isBlank();
    }

    private static boolean isSchoolNameValid(String name){
        return !isNull(name) && !name.isBlank();
    }
}
