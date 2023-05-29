package com.studentbox.api.entities.jobposition;

import com.studentbox.api.models.jobposition.JobPositionCreationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="job_positions")
@NoArgsConstructor
public class JobPosition {

    @Id
    private UUID id;

    @Column(name="name", length = 100)
    private String name;

    public JobPosition(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public JobPosition(JobPositionCreationModel jobPositionCreationModel) {
        this.id = UUID.randomUUID();
        this.name = jobPositionCreationModel.getName();
    }
}
