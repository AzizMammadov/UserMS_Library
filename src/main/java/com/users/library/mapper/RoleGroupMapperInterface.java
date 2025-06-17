package com.users.library.mapper;

import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;

import java.util.Set;

public interface RoleGroupMapperInterface {

    RoleGroupResponseDto toDto(RoleGroup group);

    RoleGroupShortDto toShortDto(RoleGroup group);

    RoleGroup toEntity(RoleGroupRequestDto dto, Set<Role> roles);
}
