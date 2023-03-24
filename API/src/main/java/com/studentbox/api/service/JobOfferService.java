package com.studentbox.api.service;

import com.studentbox.api.entities.company.JobOffer;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.joboffer.JobOfferModel;

import java.util.List;

public interface JobOfferService {

    List<JobOfferModel> getPage(PaginationModel paginationModel, String id);
    JobOffer findById(String id);
    JobOfferModel findBasicById(String id);
    void create(JobOfferCreationModel jobOfferCreationModel);
    void delete(String id);
}
