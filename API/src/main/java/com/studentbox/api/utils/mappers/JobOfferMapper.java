package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.models.company.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.company.joboffer.JobOfferModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class JobOfferMapper {
    private JobOfferMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static JobOfferModel mapToModel(JobOffer jobOffer){
        JobOfferModel model = new JobOfferModel();

        model.setId(jobOffer.getId());
        model.setDescription(jobOffer.getDescription());
        model.setJobPosition(JobPositionMapper.mapToModel(jobOffer.getJobPosition()));
        model.setCompany(CompanyMapper.mapToModel(jobOffer.getCompany()));
        model.setSkills(SkillMapper.mapAllToModel(jobOffer.getSkills()));
        model.setSalary(jobOffer.getSalary());
        model.setCreatedAt(jobOffer.getCreatedAt().toLocalDateTime());
        model.setModifiedAt(jobOffer.getModifiedAt().toLocalDateTime());

        return model;
    }

    public static List<JobOfferModel> mapAllToModel(List<JobOffer> jobOffers){
        return jobOffers
                .stream()
                .map(JobOfferMapper::mapToModel)
                .toList();
    }

    public static JobOffer mapFromCreationModel(JobOfferCreationModel jobOfferCreationModel) {
        JobOffer jobOffer = new JobOffer();

        jobOffer.setId(UUID.randomUUID());
        jobOffer.setCompany(jobOfferCreationModel.getCompany());
        jobOffer.setJobPosition(jobOfferCreationModel.getJobPosition());
        jobOffer.setDescription(jobOfferCreationModel.getDescription());
        jobOffer.setSalary(jobOfferCreationModel.getSalary());
        jobOffer.setCreatedAt(Timestamp.from(Instant.now()));
        jobOffer.setModifiedAt(jobOffer.getModifiedAt());
        jobOffer.setSkills(new LinkedList<>());

        return jobOffer;
    }
}
