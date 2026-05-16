package com.example.product_api.service;

import com.example.product_api.dto.*;
import com.example.product_api.model.User;
import com.example.product_api.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO create(
            UserRequestDTO dto
    ) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        User savedUser =
                repository.save(user);

        return mapToResponse(savedUser);
    }

    public List<UserResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponseDTO findById(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado"
                        )
                );

        return mapToResponse(user);
    }

    public UserResponseDTO update(
            UUID id,
            UserRequestDTO dto
    ) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado"
                        )
                );

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        User updatedUser =
                repository.save(user);

        return mapToResponse(updatedUser);
    }

    public void delete(UUID id) {

        repository.deleteById(id);
    }

    private UserResponseDTO mapToResponse(
            User user
    ) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getActive()
        );
    }
}