package com.users.library.dto.response;

import java.util.Set;

public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<RoleGroupShortDto> roleGroups;
}

