package com.example.product_api.controller;

import com.example.product_api.dto.*;
import com.example.product_api.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")

@CrossOrigin("*")

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDTO create(
            @Valid
            @RequestBody UserRequestDTO dto
    ) {
        return service.create(dto);
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(
            @PathVariable UUID id
    ) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable UUID id,
            @Valid
            @RequestBody UserRequestDTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id
    ) {
        service.delete(id);
    }
}