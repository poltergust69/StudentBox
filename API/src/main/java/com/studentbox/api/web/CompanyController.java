package com.studentbox.api.web;

import com.studentbox.api.common.PermissionEvaluator;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.models.company.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.company.joboffer.JobOfferModel;
import com.studentbox.api.service.company.CompanyService;
import com.studentbox.api.service.company.JobOfferService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
@CrossOrigin("*")
public class CompanyController {

    JobOfferService jobOfferService;
    CompanyService companyService;

    @GetMapping("/job-posts")
    @ApiOperation(value="Get a page of the logged-in company's job offer posts", response = JobOfferModel[].class)
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<List<JobOfferModel>> getPageOfOffers(
            PaginationModel paginationModel
    ){
        return ResponseEntity.ok(jobOfferService.getPage(paginationModel));
    }

    @PostMapping("/job-posts")
    @ApiOperation(value="Create a new job offer.")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity createPost(
            JobOfferCreationModel jobOfferCreationModel
    ){
        jobOfferService.create(jobOfferCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/job-posts/{id}")
    @ApiOperation(value="Delete a job offer.")
    @PreAuthorize("hasRole('ROLE_COMPANY') && @permissionEvaluator.hasPermissionToAlterJobOffer(principal, #id)")
    public ResponseEntity deletePost(
            @PathVariable String id,
            PermissionEvaluator permissionEvaluator
    ){
        jobOfferService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register")
    public ResponseEntity register(
            RegisterCompanyDetails registerCompanyDetails
    ) {
        companyService.registerCompany(registerCompanyDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
