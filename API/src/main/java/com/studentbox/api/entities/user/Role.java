package com.studentbox.api.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.ROLE_PREFIX;

@Data
@Entity
@Table(name="roles")
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    @Transient
    private String authority;

    public Role(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + name;
    }
}
