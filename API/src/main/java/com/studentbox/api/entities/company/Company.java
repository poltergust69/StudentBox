package com.studentbox.api.entities.company;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="companies")
@NoArgsConstructor
public class Company {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name="company_name")
    private String name;
}
