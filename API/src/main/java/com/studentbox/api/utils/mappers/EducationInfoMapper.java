package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.models.student.education.EducationInfoModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class EducationInfoMapper {
    private EducationInfoMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static EducationInfoModel mapToModel(StudentEducation studentEducation){
        return new EducationInfoModel(studentEducation);
    }

    public static List<EducationInfoModel> mapAllToModel(List<StudentEducation> studentEducations){
        return studentEducations
                .stream()
                .map(EducationInfoMapper::mapToModel)
                .toList();
    }
}
