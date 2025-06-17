package com.users.library.mapper;

import com.users.library.dto.request.UserRequestDto;
import com.users.library.dto.response.UserResponseDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.entity.User;

import java.util.Set;

public interface UserMapperInterface {

    User toEntity(UserRequestDto dto, Set<Role> roles, Set<RoleGroup> roleGroups);

    UserResponseDto toDto(User user);
}
