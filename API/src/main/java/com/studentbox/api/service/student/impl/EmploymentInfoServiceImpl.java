package com.studentbox.api.service.student.impl;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;
import com.studentbox.api.repository.student.EmploymentInfoRepository;
import com.studentbox.api.service.company.CompanyService;
import com.studentbox.api.service.jobposition.JobPositionService;
import com.studentbox.api.service.student.EmploymentInfoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.EMPLOYMENT_INFO_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class EmploymentInfoServiceImpl implements EmploymentInfoService {

    private final EmploymentInfoRepository employmentInfoRepository;
    private final JobPositionService jobPositionService;
    private final CompanyService companyService;

    private final Logger logger = LoggerFactory.getLogger(EmploymentInfoServiceImpl.class);

    @Override
    public EmploymentInfo findById(String id) {
        return employmentInfoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(String.format(EMPLOYMENT_INFO_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public void addEmploymentInfoToStudent(Student student, EmploymentInfoCreationModel employmentInfoCreationModel) {
        JobPosition jobPosition = jobPositionService.findByName(employmentInfoCreationModel.getJobPosition());
        Company company = companyService.findByName(employmentInfoCreationModel.getCompanyName());

        EmploymentInfo employmentInfo = new EmploymentInfo(employmentInfoCreationModel, student, jobPosition, company);

        try{
            employmentInfoRepository.save(employmentInfo);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteEmploymentInfo(String id) {
        EmploymentInfo employmentInfo = findById(id);

        employmentInfoRepository.delete(employmentInfo);
    }

}
