package com.studentbox.api.service.impl;

import com.studentbox.api.entities.company.JobOffer;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.joboffer.JobOfferModel;
import com.studentbox.api.repository.JobOfferRepository;
import com.studentbox.api.service.JobOfferService;
import com.studentbox.api.utils.mappers.JobOfferMapper;
import com.studentbox.api.utils.validators.JobOfferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.POSTS_DEFAULT_SORT_BY;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    JobOfferRepository jobOfferRepository;

    private final static Logger logger = LoggerFactory.getLogger(JobOfferServiceImpl.class);

    @Override
    public List<JobOfferModel> getPage(PaginationModel paginationModel, String companyId) {
        Pageable pageable = PageRequest.of(paginationModel.getPageIndex(), paginationModel.getPageSize(), POSTS_DEFAULT_SORT_BY);
        var jobOffers = jobOfferRepository.findByCompanyId(companyId, pageable);

        return JobOfferMapper.mapAllToModel(jobOffers.toList());
    }

    @Override
    public JobOffer findById(String id) {
        UUID jobOfferId = UUID.fromString(id);
        return jobOfferRepository.findById(jobOfferId).orElseThrow(
                () -> new NotFoundException(String.format(JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE, id))
        );
    }

    @Override
    public JobOfferModel findBasicById(String id) {
        var jobOffer = findById(id);
        return JobOfferMapper.mapToModel(jobOffer);
    }

    @Override
    public void create(JobOfferCreationModel jobOfferCreationModel) {
        JobOfferValidator.validateJobOffer(jobOfferCreationModel);

        JobOffer jobOffer = new JobOffer(jobOfferCreationModel);

        try{
            jobOfferRepository.save(jobOffer);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        var jobOffer = findById(id);

        jobOfferRepository.delete(jobOffer);
    }
}
