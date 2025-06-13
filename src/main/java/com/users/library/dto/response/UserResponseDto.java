package com.users.library.dto.response;



import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private boolean active;
    private Set<RoleShortDto> roles;
    private Set<RoleGroupShortDto> roleGroups;
}

