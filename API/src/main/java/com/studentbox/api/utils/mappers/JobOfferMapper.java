package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.company.JobOffer;
import com.studentbox.api.models.joboffer.JobOfferModel;

import java.util.List;

public class JobOfferMapper {

    public static JobOfferModel mapToModel(JobOffer jobOffer){
        return new JobOfferModel(jobOffer);
    }

    public static List<JobOfferModel> mapAllToModel(List<JobOffer> jobOffers){
        return jobOffers.stream().map(JobOfferMapper::mapToModel).toList();
    }
}
