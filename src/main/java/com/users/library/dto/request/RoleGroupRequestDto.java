package com.users.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleGroupRequestDto {

    @NotBlank(message = "Role group name cannot be blank")
    @Size(max = 100, message = "Role group name must be less than 100 characters")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
}

