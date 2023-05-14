package com.studentbox.api.entities.user;

import com.studentbox.api.entities.user.enums.RoleType;
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

    @Column(unique = true)
    private String username;

    private String password;

    private String avatarUrl;

    @ManyToOne
    private Role role;

    public User(String email, String username, String password, Role role) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean hasRole(RoleType roleType){
        return role.getName().equals(roleType.name());
    }
}
