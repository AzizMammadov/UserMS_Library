package com.users.library.controller;


import com.users.library.dto.request.RoleGroupRequestDto;
import com.users.library.dto.response.RoleGroupResponseDto;
import com.users.library.service.RoleGroupServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rolegroups")
@RequiredArgsConstructor
@Validated
public class RoleGroupController {

    private final RoleGroupServiceInterface roleGroupService;

    @GetMapping
    public ResponseEntity<List<RoleGroupResponseDto>> getAll() {
        List<RoleGroupResponseDto> list = roleGroupService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleGroupResponseDto> getById(@PathVariable Long id) {
        RoleGroupResponseDto dto = roleGroupService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RoleGroupResponseDto> create(@Valid @RequestBody RoleGroupRequestDto requestDto) {
        RoleGroupResponseDto dto = roleGroupService.create(requestDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleGroupResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleGroupRequestDto requestDto) {
        RoleGroupResponseDto dto = roleGroupService.update(id, requestDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
