package com.users.library.mapper;

import com.users.library.dto.request.UserRequestDto;
import com.users.library.dto.response.UserResponseDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.entity.User;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {


    public static User toEntity(UserRequestDto dto, Set<Role> roles, Set<RoleGroup> roleGroups) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setActive(dto.isActive());
        user.setRoles(roles != null ? roles : new HashSet<>());
        user.setRoleGroups(roleGroups != null ? roleGroups : new HashSet<>());
        return user;
    }


    public static UserResponseDto toDto(User user) {
        if (user == null) return null;

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setActive(user.isActive());

        dto.setRoles(
                user.getRoles() != null
                        ? user.getRoles().stream().map(UserMapper::mapToRoleShortDto).collect(Collectors.toSet())
                        : new HashSet<>()
        );

        dto.setRoleGroups(
                user.getRoleGroups() != null
                        ? user.getRoleGroups().stream().map(UserMapper::mapToRoleGroupShortDto).collect(Collectors.toSet())
                        : new HashSet<>()
        );

        return dto;
    }


    private static RoleShortDto mapToRoleShortDto(Role role) {
        RoleShortDto dto = new RoleShortDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }


    private static RoleGroupShortDto mapToRoleGroupShortDto(RoleGroup group) {
        RoleGroupShortDto dto = new RoleGroupShortDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }

}

