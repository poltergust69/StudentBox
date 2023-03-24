package com.studentbox.api.web;

import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.jobposition.JobPositionCreationModel;
import com.studentbox.api.models.jobposition.JobPositionModel;
import com.studentbox.api.service.JobPositionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-positions")
@AllArgsConstructor
public class JobPositionController{

    private final JobPositionService jobPositionService;

    @GetMapping
    @ApiOperation(value="Get a page of job positions listed in the system.", response = JobPositionModel[].class)
    public ResponseEntity<List<JobPositionModel>> getPageOfJobPositions(
            PaginationModel paginationModel
    ){
        return ResponseEntity.ok(jobPositionService.getPage(paginationModel));
    }

    @PostMapping
    @ApiOperation(value="Create a new job position.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createJobPosition(
            JobPositionCreationModel jobPositionCreationModel
    ){
        try{
            jobPositionService.create(jobPositionCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{positionId}")
    @ApiOperation(value="Delete a job position.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteJobPosition(
            @PathVariable String positionId
    ){
        jobPositionService.delete(positionId);
        return ResponseEntity.ok().build();
    }

}