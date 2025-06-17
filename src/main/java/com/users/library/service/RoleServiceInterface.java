package com.users.library.service;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleResponseDto;

import java.util.List;

public interface RoleServiceInterface {

    RoleResponseDto create(RoleRequestDto dto);

    RoleResponseDto getById(Long id);

    List<RoleResponseDto> getAll();

    RoleResponseDto update(Long id, RoleRequestDto dto);

    void delete(Long id);
}
