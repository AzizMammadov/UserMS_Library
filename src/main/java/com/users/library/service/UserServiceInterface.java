package com.users.library.service;

import com.users.library.dto.request.UserRequestDto;
import com.users.library.dto.response.UserResponseDto;

import java.util.List;

public interface UserServiceInterface {

    UserResponseDto create(UserRequestDto dto);

    UserResponseDto getById(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto update(Long id, UserRequestDto dto);

    void delete(Long id);
}
