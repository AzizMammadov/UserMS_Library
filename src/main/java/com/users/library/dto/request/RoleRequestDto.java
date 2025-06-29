package com.users.library.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RoleRequestDto {
    private String name;
    private String description;

    private Set<Long> roleGroupIds;

}
