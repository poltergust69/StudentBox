package com.studentbox.api.service.employ.impl;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.employ.AcceptanceStatus;
import com.studentbox.api.entities.employ.JobOfferStatus;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.company.joboffer.JobOfferModel;
import com.studentbox.api.models.student.StudentModel;
import com.studentbox.api.repository.company.JobOfferRepository;
import com.studentbox.api.repository.employ.JobOfferStatusesRepository;
import com.studentbox.api.repository.student.StudentRepository;
import com.studentbox.api.repository.user.UserRepository;
import com.studentbox.api.service.employ.EmployService;
import com.studentbox.api.utils.containers.SharedMethodContainer;
import com.studentbox.api.utils.mappers.JobOfferMapper;
import com.studentbox.api.utils.mappers.StudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.studentbox.api.utils.containers.ConstantsContainer.MINIMUM_PERCENTAGE_MATCHING_SKILLS;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.ParameterConstantsContainer.STUDENT_ID_PARAM;
import static com.studentbox.api.utils.containers.SharedMethodContainer.calculateMatchingSkillsPercentage;
import static com.studentbox.api.utils.containers.SharedMethodContainer.getAuthenticatedUserId;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class EmployServiceImpl implements EmployService {
    private final UserRepository userRepository;
    private final JobOfferStatusesRepository jobOfferStatusesRepository;
    private final StudentRepository studentRepository;
    private final JobOfferRepository jobOfferRepository;

    @Override
    public List<StudentModel> getStudentsApplicableForJobOffer(UUID jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new NotFoundException(String.format(JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE, jobOfferId)));

        return StudentMapper.mapAllToModel(
                studentRepository.findAll()
                        .stream()
                        .filter(
                                student -> {
                                    var jobOfferStatus = jobOfferStatusesRepository.findByStudentAndJobOffer(student, jobOffer);

                                    if(jobOfferStatus.isEmpty() || jobOfferStatus.get().getCompanyStatus().equals(AcceptanceStatus.WAITING)){
                                        return calculateMatchingSkillsPercentage(jobOffer, student) * 100 > MINIMUM_PERCENTAGE_MATCHING_SKILLS;
                                    }

                                    return false;
                                }
                        )
                        .toList()
        );
    }

    @Override
    public List<JobOfferModel> getJobOffersApplicableForStudent() {
        UUID studentId = getAuthenticatedUserId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(String.format(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE, studentId)));

        return JobOfferMapper.mapAllToModel(
                jobOfferRepository.findAll()
                        .stream()
                        .filter(
                                jobOffer -> {
                                    var jobOfferStatus = jobOfferStatusesRepository.findByStudentAndJobOffer(student, jobOffer);

                                    if(jobOfferStatus.isEmpty() || jobOfferStatus.get().getStudentStatus().equals(AcceptanceStatus.WAITING)){
                                        return calculateMatchingSkillsPercentage(jobOffer, student) * 100 > MINIMUM_PERCENTAGE_MATCHING_SKILLS;
                                    }

                                    return false;
                                }
                        )
                        .toList()
        );
    }

    @Override
    public void swipe(UUID jobOfferId, UUID studentId, AcceptanceStatus acceptanceStatus) {
        UUID authenticatedUserId = SharedMethodContainer.getAuthenticatedUserId();

        User authenticatedUser = userRepository.findById(authenticatedUserId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, authenticatedUserId)));

        if(authenticatedUser.hasRole(RoleType.COMPANY) && isNull(studentId)){
            throw new NotValidException(String.format(PARAMETER_REQUIRED_EXCEPTION_MESSAGE, STUDENT_ID_PARAM));
        }

        Student student = studentRepository.findById(authenticatedUser.hasRole(RoleType.COMPANY) ? studentId : authenticatedUserId)
                .orElseThrow(() -> new NotFoundException(String.format(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE, studentId)));

        JobOffer jobOffer = this.jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new NotFoundException(JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE));

        createJobOfferStatusIfNotExists(jobOffer, student);

        JobOfferStatus jobOfferStatus;

        if (authenticatedUser.hasRole(RoleType.COMPANY)){
            jobOfferStatus = this.jobOfferStatusesRepository.findByJobOfferAndCompanyId(jobOffer, authenticatedUserId);
            jobOfferStatus.setCompanyStatus(acceptanceStatus);
        }
        else{
            jobOfferStatus = this.jobOfferStatusesRepository.findByJobOfferAndStudentId(jobOffer, authenticatedUserId);
            jobOfferStatus.setStudentStatus(acceptanceStatus);
        }

        jobOfferStatusesRepository.save(jobOfferStatus);
    }

    private void createJobOfferStatusIfNotExists(JobOffer jobOffer, Student student){
        JobOfferStatus jobOfferStatus = new JobOfferStatus();

        jobOfferStatus.setId(UUID.randomUUID());
        jobOfferStatus.setJobOffer(jobOffer);

        jobOfferStatus.setCompany(jobOffer.getCompany());
        jobOfferStatus.setCompanyStatus(AcceptanceStatus.WAITING);

        jobOfferStatus.setStudent(student);
        jobOfferStatus.setStudentStatus(AcceptanceStatus.WAITING);

        jobOfferStatusesRepository.saveAndFlush(jobOfferStatus);
    }
}
