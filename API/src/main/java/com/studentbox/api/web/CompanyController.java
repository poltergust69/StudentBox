package com.studentbox.api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.models.joboffer.JobOfferCreationModel;
import com.studentbox.api.models.joboffer.JobOfferModel;
import com.studentbox.api.service.CompanyService;
import com.studentbox.api.service.JobOfferService;
import com.studentbox.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    JobOfferService jobOfferService;
    UserService userService;
    CompanyService companyService;

    @GetMapping("/job-posts")
    @ApiOperation(value="Get a page of the logged-in company's job offer posts", response = JobOfferModel[].class)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<JobOfferModel>> getPageOfOffers(
            PaginationModel paginationModel
    ){
        User company = userService.findAuthenticatedUser();
        return ResponseEntity.ok(jobOfferService.getPage(paginationModel, company.getId().toString()));
    }

    @PostMapping("/job-posts")
    @ApiOperation(value="Create a new job offer.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(
            JobOfferCreationModel jobOfferCreationModel
    ){
        try{
            jobOfferService.create(jobOfferCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/job-posts/{id}")
    @ApiOperation(value="Delete a job offer.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deletePost(
            @PathVariable String id
    ){
        jobOfferService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(
            AuthRequestModel authRequestModel
    ) throws JsonProcessingException {
        return ResponseEntity.ok(userService.login(authRequestModel));
    }

    @PostMapping("/register")
    public ResponseEntity register(
            RegisterCompanyDetails registerCompanyDetails
    ) {
        companyService.registerCompany(registerCompanyDetails);
        return ResponseEntity.ok().build();
    }
}
