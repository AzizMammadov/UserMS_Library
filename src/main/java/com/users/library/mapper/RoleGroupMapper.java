package com.users.library.mapper;

import com.users.library.dto.response.RoleGroupResponseDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleGroupMapper {


    public static RoleGroupResponseDto toDto(RoleGroup group) {
        if (group == null) return null;

        RoleGroupResponseDto dto = new RoleGroupResponseDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());

        dto.setRoles(
                group.getRoles() != null
                        ? group.getRoles().stream()
                        .map(RoleGroupMapper::mapToRoleShortDto)
                        .collect(Collectors.toSet())
                        : new HashSet<>()
        );

        return dto;
    }


    public static RoleGroupShortDto toShortDto(RoleGroup group) {
        if (group == null) return null;

        RoleGroupShortDto dto = new RoleGroupShortDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }


    private static RoleShortDto mapToRoleShortDto(Role role) {
        if (role == null) return null;

        RoleShortDto dto = new RoleShortDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
