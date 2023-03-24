package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.jobposition.JobPositionCreationModel;

import static com.studentbox.api.utils.containers.ConstantsContainer.JOB_POSITION_NAME_MAX_LENGTH;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class JobPositionValidator {
    public static void validateJobPosition(JobPositionCreationModel jobPositionCreationModel){
        if(!isNameValid(jobPositionCreationModel.getName())){
            throw new NotValidException(NOT_VALID_JOB_POSITION_NAME_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isNameValid(String name){
        return !isNull(name) && !name.isBlank() && name.length() <= JOB_POSITION_NAME_MAX_LENGTH;
    }
}
