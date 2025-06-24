package com.users.library.dto.response;



import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private boolean deleted;
    private boolean active;
    private Set<RoleShortDto> roles;
    private Set<RoleGroupShortDto> roleGroups;
}

