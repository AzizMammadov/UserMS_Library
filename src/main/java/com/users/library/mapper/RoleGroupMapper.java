package com.users.library.mapper;

import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;
import com.users.library.dto.response.RoleGroupShortDto;
import com.users.library.dto.response.RoleShortDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleGroupMapper implements RoleGroupMapperInterface {

    @Override
    public RoleGroupResponseDto toDto(RoleGroup group) {
        if (group == null) return null;

        RoleGroupResponseDto dto = new RoleGroupResponseDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());

        dto.setRoles(group.getRoles() != null
                ? group.getRoles().stream()
                .map(this::mapToRoleShortDto)
                .collect(Collectors.toSet())
                : new HashSet<>());

        return dto;
    }

    @Override
    public RoleGroupShortDto toShortDto(RoleGroup group) {
        if (group == null) return null;

        RoleGroupShortDto dto = new RoleGroupShortDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }

    @Override
    public RoleGroup toEntity(RoleGroupRequestDto dto, Set<Role> roles) {
        if (dto == null) return null;

        RoleGroup group = new RoleGroup();
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        group.setRoles(roles != null ? roles : new HashSet<>());
        return group;
    }

    private RoleShortDto mapToRoleShortDto(Role role) {
        if (role == null) return null;

        RoleShortDto dto = new RoleShortDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
