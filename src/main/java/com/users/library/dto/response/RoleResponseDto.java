package com.users.library.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<RoleGroupShortDto> roleGroups;
}

