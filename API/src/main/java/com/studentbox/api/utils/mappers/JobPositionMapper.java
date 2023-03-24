package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.models.jobposition.JobPositionModel;

import java.util.List;

public class JobPositionMapper {

    public static JobPositionModel mapToModel(JobPosition jobPosition){
        return new JobPositionModel(jobPosition);
    }

    public static List<JobPositionModel> mapAllToModel(List<JobPosition> jobPositions){
        return jobPositions.stream().map(JobPositionMapper::mapToModel).toList();
    }
}
