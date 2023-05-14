package com.studentbox.api.web.rest;


import com.studentbox.api.common.LoggedUser;
import com.studentbox.api.entities.employ.StudentJobOffer;
import com.studentbox.api.models.employ.SortedStudentJobOffer;
import com.studentbox.api.models.jobposition.JobPositionModel;
import com.studentbox.api.service.employ.EmployService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employ")
@AllArgsConstructor
public class EmployRestController {

    private final EmployService employService;

    @GetMapping("/student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @ApiOperation(value="Get list of job offers for a student", response = JobPositionModel[].class)
    public ResponseEntity<Collection<SortedStudentJobOffer>> getJobOffersForStudent(
    ){
        return ResponseEntity.ok(employService.offers(LoggedUser.getLoggedUser().getId()));
    }


    @GetMapping("/company")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @ApiOperation(value="Get list of students for job", response = JobPositionModel[].class)
    public ResponseEntity<Collection<SortedStudentJobOffer>> getStudentsFittedForJobOffers(
    ){
        return ResponseEntity.ok(employService.students(LoggedUser.getLoggedUser().getId()));
    }

    @PostMapping("/swipeRight")
    @PreAuthorize("hasAnyRole('ROLE_COMPANY','ROLE_STUDENT')")
    @ApiOperation(value="Swipe right for a job or student", response = JobPositionModel[].class)
    public ResponseEntity swipeRight(@Param("studentId") UUID studentId, @Param("jobOfferId")UUID jobOfferId
                                     ){
        return ResponseEntity.ok(employService.swipeRight(LoggedUser.getLoggedUser(),studentId, jobOfferId));
    }

    @PostMapping("/swipeLeft")
    @PreAuthorize("hasAnyRole('ROLE_COMPANY','ROLE_STUDENT')")
    @ApiOperation(value="Swipe left for a job or student", response = JobPositionModel[].class)
    public ResponseEntity swipeLeft(@Param("studentId") UUID studentId, @Param("jobOfferId")UUID jobOfferId
    ){
        return ResponseEntity.ok(employService.swipeLeft(LoggedUser.getLoggedUser(),studentId, jobOfferId));
    }


    @PostMapping("/allMatched")
    @PreAuthorize("hasAnyRole('ROLE_COMPANY','ROLE_STUDENT')")
    @ApiOperation(value="Get all matched students or jobs", response = JobPositionModel[].class)
    public ResponseEntity<Collection<StudentJobOffer>> getMatched(
    ){
        return ResponseEntity.ok(employService.findAllMatched(LoggedUser.getLoggedUser().getId()));
    }


}
