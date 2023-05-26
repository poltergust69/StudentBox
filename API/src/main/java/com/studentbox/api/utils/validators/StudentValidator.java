package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.student.RegisterStudentDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.SharedMethodContainer.*;

@Component
@AllArgsConstructor
public class StudentValidator {
    private final UserValidator userValidator;
    public void validateStudentRegistrationDetails(RegisterStudentDetails studentDetails){
        userValidator.validateUserRegistrationDetails(studentDetails);

        if(isNameInvalid(studentDetails.getFirstName())){
            throw new NotValidException(NOT_VALID_STUDENT_FIRST_NAME_EXCEPTION_MESSAGE);
        }

        if(isNameInvalid(studentDetails.getLastName())){
            throw new NotValidException(NOT_VALID_STUDENT_LAST_NAME_EXCEPTION_MESSAGE);
        }

        if(isDescriptionInvalid(studentDetails.getDescription())){
            throw new NotValidException(NOT_VALID_STUDENT_DESCRIPTION_EXCEPTION_MESSAGE);
        }

        if(isDateOfBirthInvalid(studentDetails.getDateOfBirth())){
            throw new NotValidException(NOT_VALID_STUDENT_DATE_OF_BIRTH_EXCEPTION_MESSAGE);
        }
    }
}
