package com.users.library.service;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleResponseDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.mapper.RoleMapper;
import com.users.library.repository.RoleGroupRepository;
import com.users.library.repository.RoleRepository;
import com.users.library.service.RoleServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;
    private final RoleGroupRepository roleGroupRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository,
                       RoleGroupRepository roleGroupRepository,
                       RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleGroupRepository = roleGroupRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDto create(RoleRequestDto dto) {
        Set<RoleGroup> roleGroups = new HashSet<>();
        if (dto.getRoleGroupIds() != null) {
            roleGroups = dto.getRoleGroupIds().stream()
                    .map(roleGroupRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        Role role = roleMapper.toEntity(dto, roleGroups);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    @Override
    public RoleResponseDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());  // toList() -> collect(Collectors.toList())
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto dto) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        existingRole.setName(dto.getName());
        existingRole.setDescription(dto.getDescription());


        if (dto.getRoleGroupIds() != null) {
            Set<RoleGroup> roleGroups = dto.getRoleGroupIds().stream()
                    .map(roleGroupRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            existingRole.setRoleGroups(roleGroups);
        }

        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toDto(updatedRole);
    }

    @Override
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
}
