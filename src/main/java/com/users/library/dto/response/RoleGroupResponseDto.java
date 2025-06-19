package com.users.library.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class RoleGroupResponseDto {

    private Long id;
    private String name;
    private String description;

    private Set<RoleShortDto> roles;
}
