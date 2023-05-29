package com.studentbox.api.service.jobposition.impl;

import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.jobposition.JobPositionCreationModel;
import com.studentbox.api.models.jobposition.JobPositionModel;
import com.studentbox.api.repository.jobposition.JobPositionRepository;
import com.studentbox.api.service.jobposition.JobPositionService;
import com.studentbox.api.utils.mappers.JobPositionMapper;
import com.studentbox.api.utils.validators.JobPositionValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.JOB_POSITION_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    private final Logger logger = LoggerFactory.getLogger(JobPositionServiceImpl.class);

    @Override
    public List<JobPositionModel> getPage(PaginationModel paginationModel) {
        Pageable pageable = PageRequest.of(paginationModel.getPageIndex(), paginationModel.getPageSize());
        var jobPositions = jobPositionRepository.findAll(pageable);

        return JobPositionMapper.mapAllToModel(jobPositions.toList());
    }

    @Override
    public JobPosition findById(String id) {
        UUID jobPositionId = UUID.fromString(id);
        return jobPositionRepository.findById(jobPositionId).orElseThrow(
                () -> new NotFoundException(String.format(JOB_POSITION_NOT_FOUND_EXCEPTION_MESSAGE, id))
        );
    }

    @Override
    public JobPositionModel findBasicById(String id) {
        var jobPosition = findById(id);
        return JobPositionMapper.mapToModel(jobPosition);
    }

    @Override
    public void create(JobPositionCreationModel jobPositionCreationModel) {
        JobPositionValidator.validateJobPosition(jobPositionCreationModel);

        JobPosition jobPosition = new JobPosition(jobPositionCreationModel);

        try{
            jobPositionRepository.save(jobPosition);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        var jobPosition = findById(id);

        jobPositionRepository.delete(jobPosition);
    }

    @Override
    public JobPosition findByName(String name) {
        return jobPositionRepository.findByName(name);
    }
}
