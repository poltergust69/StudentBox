package com.studentbox.api.repository.employ;


import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.employ.AcceptanceStatus;
import com.studentbox.api.entities.employ.JobOfferStatus;
import com.studentbox.api.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobOfferStatusesRepository extends JpaRepository<JobOfferStatus, UUID> {
    JobOfferStatus findByJobOfferAndStudent(JobOffer jobOffer, Student student);
    JobOfferStatus findByJobOfferAndStudentId(JobOffer jobOffer, UUID studentId);
    JobOfferStatus findByJobOfferAndCompanyId(JobOffer jobOffer, UUID companyId);
    List<JobOfferStatus> findAllByCompanyIdAndCompanyStatus(UUID companyId, AcceptanceStatus companyStatus);
    List<JobOfferStatus> findAllByCompanyIdAndCompanyStatusNot(UUID companyId, AcceptanceStatus companyStatus);
    List<JobOfferStatus> findAllByStudentStatus(AcceptanceStatus studentStatus);
    List<JobOfferStatus> findAllByStudentStatusNot(AcceptanceStatus studentStatus);
    Optional<JobOfferStatus> findByStudentAndJobOffer(Student student, JobOffer jobOffer);
}
