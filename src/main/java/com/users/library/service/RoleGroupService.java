package com.users.library.service;

import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.mapper.RoleGroupMapper;
import com.users.library.repository.RoleGroupRepository;
import com.users.library.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleGroupService implements RoleGroupServiceInterface {

    private final RoleGroupRepository roleGroupRepository;
    private final RoleGroupMapper roleGroupMapper;
    private final RoleRepository roleRepository;

    public RoleGroupService(RoleGroupRepository roleGroupRepository,
                            RoleGroupMapper roleGroupMapper,
                            RoleRepository roleRepository) {
        this.roleGroupRepository = roleGroupRepository;
        this.roleGroupMapper = roleGroupMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleGroupResponseDto create(RoleGroupRequestDto dto) {
        Set<Role> roles = new HashSet<>();
        if (dto.getRoleIds() != null) {
            roles = dto.getRoleIds().stream()
                    .map(roleRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        RoleGroup roleGroup = roleGroupMapper.toEntity(dto, roles);
        RoleGroup saved = roleGroupRepository.save(roleGroup);
        return roleGroupMapper.toDto(saved);
    }

    @Override
    public RoleGroupResponseDto getById(Long id) {
        RoleGroup roleGroup = roleGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleGroup not found with id: " + id));
        return roleGroupMapper.toDto(roleGroup);
    }

    @Override
    public List<RoleGroupResponseDto> getAll() {
        return roleGroupRepository.findAll().stream()
                .map(roleGroupMapper::toDto)
                .toList();
    }

    @Override
    public RoleGroupResponseDto update(Long id, RoleGroupRequestDto dto) {
        RoleGroup existing = roleGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleGroup not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        // Yenilənmiş role-ları da yüklə
        Set<Role> roles = new HashSet<>();
        if (dto.getRoleIds() != null) {
            roles = dto.getRoleIds().stream()
                    .map(roleRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        existing.setRoles(roles);

        RoleGroup updated = roleGroupRepository.save(existing);
        return roleGroupMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!roleGroupRepository.existsById(id)) {
            throw new RuntimeException("RoleGroup not found with id: " + id);
        }
        roleGroupRepository.deleteById(id);
    }
}
