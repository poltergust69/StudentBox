package com.studentbox.api.entities.education;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="educations")
@NoArgsConstructor
public class Education {

    @Id
    private UUID id;

    @Column(length = 250)
    private String name;
}
