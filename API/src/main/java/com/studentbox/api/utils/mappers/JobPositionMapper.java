package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.models.jobposition.JobPositionModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class JobPositionMapper {
    private JobPositionMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static JobPositionModel mapToModel(JobPosition jobPosition){
        return new JobPositionModel(jobPosition);
    }

    public static List<JobPositionModel> mapAllToModel(List<JobPosition> jobPositions){
        return jobPositions
                .stream()
                .map(JobPositionMapper::mapToModel)
                .toList();
    }
}
