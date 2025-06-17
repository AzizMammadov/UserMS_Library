package com.users.library.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private boolean active;
    private Set<Long> roleIds;
    private Set<Long> roleGroupIds;
}
