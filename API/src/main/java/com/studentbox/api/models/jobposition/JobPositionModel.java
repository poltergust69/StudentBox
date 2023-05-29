package com.studentbox.api.models.jobposition;

import com.studentbox.api.entities.jobposition.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class JobPositionModel{

    private UUID id;
    private String name;

    public JobPositionModel(JobPosition jobPosition) {
        this.id = jobPosition.getId();
        this.name = jobPosition.getName();
    }
}