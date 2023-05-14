package com.studentbox.api.repository.employ;


import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.employ.StudentJobOffer;
import com.studentbox.api.entities.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface StudentJobOfferRepository extends JpaRepository<StudentJobOffer, UUID> {

    @Query(value = " select job from JobOffer job EXCEPT" +
            " (select job from StudentJobOffer studentjob" +
            "  join JobOffer job on studentjob.jobOffer.id = job.id" +
            " where (studentjob.studentStatus = 'A' or studentjob.studentStatus = 'D') ) ", nativeQuery = true )
    List<JobOffer> findAllNotCheckedJobsForStudent();

    @Query(value = " select job from JobOffer job where job.company.id = :comp_id  EXCEPT" +
            " (select job from StudentJobOffer studentjob" +
            "  join JobOffer job on studentjob.jobOffer.id = job.id" +
            " where (studentjob.companyStatus =  'A' or studentjob.companyStatus = 'D')) ",nativeQuery = true)
    List<JobOffer> findAllNotCheckedAllJobsForCompany(@Param("comp_id") UUID id);


    StudentJobOffer findByJobOfferAndStudent(JobOffer jobOffer, Student student);

    @Query(value = " select job from StudentJobOffer job where job.jobOffer.company.id = :comp_id and " +
            "job.studentStatus = 'A' and job.companyStatus = 'A'" +
            " order by job.score desc " )
    Collection<StudentJobOffer> findAllMatchedJobsForCompany(@Param("comp_id") UUID id);

    @Query(value = " select job from StudentJobOffer job where job.student.id = :student_id and" +
            " job.studentStatus = 'A' and job.companyStatus = 'A' order by job.score desc " )
    Collection<StudentJobOffer> findAllMatchedJobsForStudent(@Param("student_id") UUID id);
}
