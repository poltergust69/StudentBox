package com.studentbox.api.entities.company;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="companies")
@NoArgsConstructor
public class Company {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name="company_name")
    private String name;
}
