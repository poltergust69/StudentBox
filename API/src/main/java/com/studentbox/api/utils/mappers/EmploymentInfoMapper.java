package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.models.student.employment.EmploymentInfoModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class EmploymentInfoMapper {
    private EmploymentInfoMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static EmploymentInfoModel mapToModel(EmploymentInfo employmentInfo){
        EmploymentInfoModel model = new EmploymentInfoModel();

        model.setId(employmentInfo.getId());
        model.setCompanyName(employmentInfo.getCompanyName());
        model.setStartedAt(employmentInfo.getStartedAt());
        model.setEndedAt(employmentInfo.getEndedAt());
        model.setJobPosition(employmentInfo.getJobPosition().getName());

        return model;
    }

    public static List<EmploymentInfoModel> mapAllToModel(List<EmploymentInfo> employmentInfoList){
        return employmentInfoList
                .stream()
                .map(EmploymentInfoMapper::mapToModel)
                .toList();
    }
}
