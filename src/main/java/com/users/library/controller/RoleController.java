package com.users.library.controller;

import com.users.library.dto.request.RoleRequestDto;
import com.users.library.dto.response.RoleResponseDto;
import com.users.library.service.RoleServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceInterface roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@Valid @RequestBody RoleRequestDto dto) {
        return ResponseEntity.ok(roleService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequestDto dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
