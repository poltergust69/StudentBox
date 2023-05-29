package com.studentbox.api.utils.containers;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class ParameterConstantsContainer {
    private ParameterConstantsContainer(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static final String STUDENT_ID_PARAM = "studentId";
}
