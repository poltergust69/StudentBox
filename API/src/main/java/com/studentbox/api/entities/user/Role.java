package com.studentbox.api.entities.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="roles")
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    public Role(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
