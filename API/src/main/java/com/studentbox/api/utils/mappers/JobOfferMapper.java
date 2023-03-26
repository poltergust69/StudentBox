package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.models.company.joboffer.JobOfferModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class JobOfferMapper {
    private JobOfferMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static JobOfferModel mapToModel(JobOffer jobOffer){
        return new JobOfferModel(jobOffer);
    }

    public static List<JobOfferModel> mapAllToModel(List<JobOffer> jobOffers){
        return jobOffers
                .stream()
                .map(JobOfferMapper::mapToModel)
                .toList();
    }
}
