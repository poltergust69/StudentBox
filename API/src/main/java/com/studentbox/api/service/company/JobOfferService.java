package com.studentbox.api.service.company;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.company.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.company.joboffer.JobOfferModel;

import java.util.List;

public interface JobOfferService {

    List<JobOfferModel> getPage(PaginationModel paginationModel);
    JobOffer findById(String id);
    JobOfferModel findBasicById(String id);
    void create(JobOfferCreationModel jobOfferCreationModel);
    void delete(String id);
}
