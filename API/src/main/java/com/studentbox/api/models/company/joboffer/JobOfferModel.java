package com.studentbox.api.models.company.joboffer;

import com.studentbox.api.models.company.CompanyModel;
import com.studentbox.api.models.jobposition.JobPositionModel;
import com.studentbox.api.models.student.skill.SkillModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class JobOfferModel {
    private UUID id;
    private String description;
    private Long salary;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private JobPositionModel jobPosition;
    private CompanyModel company;
    private List<SkillModel> skills;
}
