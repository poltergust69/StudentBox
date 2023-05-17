package com.studentbox.api.web;


import com.studentbox.api.entities.employ.AcceptanceStatus;
import com.studentbox.api.models.company.joboffer.JobOfferModel;
import com.studentbox.api.models.jobposition.JobPositionModel;
import com.studentbox.api.models.student.StudentModel;
import com.studentbox.api.service.employ.EmployService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employ")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployMeController {

    private final EmployService employService;

    @GetMapping("/student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @ApiOperation(value="Get list of job offers for a student", response = JobPositionModel[].class)
    public ResponseEntity<List<JobOfferModel>> getJobOffersForStudent(
    ){
        return ResponseEntity.ok(employService.getJobOffersApplicableForStudent());
    }


    @GetMapping("/job-offer/{jobOfferId}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @ApiOperation(value="Get list of students for job", response = JobPositionModel[].class)
    public ResponseEntity<List<StudentModel>> getStudentsFittedForJobOffers(
            @PathVariable UUID jobOfferId
    ){
        return ResponseEntity.ok(employService.getStudentsApplicableForJobOffer(jobOfferId));
    }

    @PutMapping("/job-offer/{jobOfferId}/swipe")
    @PreAuthorize("hasAnyRole('ROLE_COMPANY','ROLE_STUDENT')")
    @ApiOperation(value="Swipe for a job for student by the company or the student", response = JobPositionModel[].class)
    public ResponseEntity swipe(
            @PathVariable UUID jobOfferId,
            @RequestParam(required = false) UUID studentId,
            @RequestParam AcceptanceStatus status
    ){
        employService.swipe(studentId, jobOfferId, status);

        return ResponseEntity.ok().build();
    }
}
