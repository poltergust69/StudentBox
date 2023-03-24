package com.studentbox.api.service;

import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.jobposition.JobPositionCreationModel;
import com.studentbox.api.models.jobposition.JobPositionModel;

import java.util.List;

public interface JobPositionService {

    List<JobPositionModel> getPage(PaginationModel paginationModel);
    JobPosition findById(String id);
    JobPositionModel findBasicById(String id);
    void create(JobPositionCreationModel jobPositionCreationModel);
    void delete(String id);
    JobPosition findByName(String name);
}
