package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.education.StudentEducation;
import com.studentbox.api.models.education.EducationInfo;

import java.util.List;

public class EducationInfoMapper {

    public static EducationInfo mapToModel(StudentEducation studentEducation){
        return new EducationInfo(studentEducation);
    }

    public static List<EducationInfo> mapAllToModel(List<StudentEducation> studentEducations){
        return studentEducations.stream().map(EducationInfoMapper::mapToModel).toList();
    }
}
