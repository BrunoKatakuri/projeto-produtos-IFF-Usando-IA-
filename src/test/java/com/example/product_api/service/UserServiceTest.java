package com.example.product_api.service;

import com.example.product_api.dto.UserRequestDTO;
import com.example.product_api.dto.UserResponseDTO;
import com.example.product_api.model.User;
import com.example.product_api.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUser() {

        UserRequestDTO dto =
                new UserRequestDTO(
                        "Bruno",
                        "bruno@email.com",
                        "123456",
                        "ADMIN",
                        true
                );

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        when(repository.save(any(User.class)))
                .thenReturn(user);

        UserResponseDTO response =
                service.create(dto);

        assertNotNull(response);

        assertEquals(
                "Bruno",
                response.getName()
        );

        verify(repository, times(1))
                .save(any(User.class));
    }
}