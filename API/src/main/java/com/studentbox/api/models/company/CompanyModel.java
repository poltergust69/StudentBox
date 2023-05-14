package com.studentbox.api.models.company;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CompanyModel {
    private UUID id;
    private String name;
    private String username;
    private String avatarUrl;
}
