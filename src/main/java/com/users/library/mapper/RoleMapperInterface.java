package com.users.library.mapper;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleResponseDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;

import java.util.Set;

public interface RoleMapperInterface {

    Role toEntity(RoleRequestDto dto, Set<RoleGroup> roleGroups);

    RoleResponseDto toDto(Role role);

    RoleShortDto toShortDto(Role role);
}
