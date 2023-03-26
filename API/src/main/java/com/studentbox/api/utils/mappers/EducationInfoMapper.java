package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.models.student.education.EducationInfo;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class EducationInfoMapper {
    private EducationInfoMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static EducationInfo mapToModel(StudentEducation studentEducation){
        return new EducationInfo(studentEducation);
    }

    public static List<EducationInfo> mapAllToModel(List<StudentEducation> studentEducations){
        return studentEducations
                .stream()
                .map(EducationInfoMapper::mapToModel)
                .toList();
    }
}
