package com.users.library.service;

import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;

import java.util.List;

public interface RoleGroupServiceInterface {
    List<RoleGroupResponseDto> getAll();
    RoleGroupResponseDto getById(Long id);
    RoleGroupResponseDto create(RoleGroupRequestDto dto);
    RoleGroupResponseDto update(Long id, RoleGroupRequestDto dto);
    void delete(Long id);
}
