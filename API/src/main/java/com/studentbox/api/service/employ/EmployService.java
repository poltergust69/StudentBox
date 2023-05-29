package com.studentbox.api.service.employ;

import com.studentbox.api.entities.employ.AcceptanceStatus;
import com.studentbox.api.models.company.joboffer.JobOfferModel;
import com.studentbox.api.models.student.StudentModel;

import java.util.List;
import java.util.UUID;

public interface EmployService {
    void swipe(UUID jobOfferId, UUID studentId, AcceptanceStatus acceptanceStatus);
    List<StudentModel> getStudentsApplicableForJobOffer(UUID jobOfferId);
    List<JobOfferModel> getJobOffersApplicableForStudent();
}
