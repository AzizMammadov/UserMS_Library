package com.users.library.mapper;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.dto.response.RoleResponseDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {


    public static Role toEntity(RoleRequestDto dto, Set<RoleGroup> roleGroups) {
        if (dto == null) return null;

        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setRoleGroups(roleGroups != null ? roleGroups : new HashSet<>());

        return role;
    }


    public static RoleResponseDto toDto(Role role) {
        if (role == null) return null;

        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());

        dto.setRoleGroups(
                role.getRoleGroups() != null
                        ? role.getRoleGroups().stream()
                        .map(RoleMapper::mapToRoleGroupShortDto)
                        .collect(Collectors.toSet())
                        : new HashSet<>()
        );

        return dto;
    }


    public static RoleShortDto toShortDto(Role role) {
        if (role == null) return null;

        RoleShortDto dto = new RoleShortDto();
        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }


    private static RoleGroupShortDto mapToRoleGroupShortDto(RoleGroup group) {
        if (group == null) return null;

        RoleGroupShortDto dto = new RoleGroupShortDto();
        dto.setId(group.getId());
        dto.setName(group.getName());

        return dto;
    }
}
