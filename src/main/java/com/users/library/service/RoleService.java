package com.users.library.service;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleResponseDto;
import com.users.library.entity.Role;
import com.users.library.mapper.RoleMapper;
import com.users.library.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponseDto create(RoleRequestDto dto) {
        Role role = roleMapper.toEntity(dto);
        Role saved = roleRepository.save(role);
        return roleMapper.toDto(saved);
    }

    @Override
    public RoleResponseDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto dto) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        existing.setName(dto.getName());
        return roleMapper.toDto(roleRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
}
