package com.users.library.service;

import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;

import java.util.List;

public interface RoleGroupServiceInterface {

    RoleGroupResponseDto create(RoleGroupRequestDto dto);

    RoleGroupResponseDto getById(Long id);

    List<RoleGroupResponseDto> getAll();

    RoleGroupResponseDto update(Long id, RoleGroupRequestDto dto);

    void delete(Long id);
}
