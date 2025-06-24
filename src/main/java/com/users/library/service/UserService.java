package com.users.library.service;

import com.users.library.dto.request.UserRequestDto;
import com.users.library.dto.response.UserResponseDto;
import com.users.library.entity.Role;
import com.users.library.entity.RoleGroup;
import com.users.library.entity.User;
import com.users.library.mapper.UserMapper;
import com.users.library.repository.RoleGroupRepository;
import com.users.library.repository.RoleRepository;
import com.users.library.repository.UserRepository;
import com.users.library.service.UserServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleGroupRepository roleGroupRepository;
    private final UserMapper userMapper;  // mapper injected as instance

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       RoleGroupRepository roleGroupRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleGroupRepository = roleGroupRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        Set<Role> roles = new HashSet<>();
        if (userRequestDto.getRoleIds() != null) {
            roles = userRequestDto.getRoleIds().stream()
                    .map(roleRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        Set<RoleGroup> roleGroups = new HashSet<>();
        if (userRequestDto.getRoleGroupIds() != null) {
            roleGroups = userRequestDto.getRoleGroupIds().stream()
                    .map(roleGroupRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        User user = userMapper.toEntity(userRequestDto, roles, roleGroups);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(userRequestDto.getUsername());
        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setFullName(userRequestDto.getFullName());
        existingUser.setActive(userRequestDto.isActive());

        Set<Role> roles = new HashSet<>();
        if (userRequestDto.getRoleIds() != null) {
            roles = userRequestDto.getRoleIds().stream()
                    .map(roleRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        existingUser.setRoles(roles);

        Set<RoleGroup> roleGroups = new HashSet<>();
        if (userRequestDto.getRoleGroupIds() != null) {
            roleGroups = userRequestDto.getRoleGroupIds().stream()
                    .map(roleGroupRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        existingUser.setRoleGroups(roleGroups);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
       // user.s  // soft delete
        userRepository.save(user);
    }
}
