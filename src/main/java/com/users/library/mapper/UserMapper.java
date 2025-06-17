package com.users.library.mapper;

import com.users.library.dto.request.UserRequestDto;
import com.users.library.dto.response.UserResponseDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.entity.User;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper implements UserMapperInterface {

    @Override
    public User toEntity(UserRequestDto dto, Set<Role> roles, Set<RoleGroup> roleGroups) {
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

    @Override
    public UserResponseDto toDto(User user) {
        if (user == null) return null;

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setActive(user.isActive());

        dto.setRoles(user.getRoles() != null
                ? user.getRoles().stream().map(this::mapToRoleShortDto).collect(Collectors.toSet())
                : new HashSet<>());

        dto.setRoleGroups(user.getRoleGroups() != null
                ? user.getRoleGroups().stream().map(this::mapToRoleGroupShortDto).collect(Collectors.toSet())
                : new HashSet<>());

        return dto;
    }

    private RoleShortDto mapToRoleShortDto(Role role) {
        if (role == null) return null;
        RoleShortDto dto = new RoleShortDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    private RoleGroupShortDto mapToRoleGroupShortDto(RoleGroup group) {
        if (group == null) return null;
        RoleGroupShortDto dto = new RoleGroupShortDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }
}
