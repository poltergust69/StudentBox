package com.studentbox.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne
    private Role role;

    public User(String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
