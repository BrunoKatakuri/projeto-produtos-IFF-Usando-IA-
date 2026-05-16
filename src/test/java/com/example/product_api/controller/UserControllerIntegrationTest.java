package com.example.product_api.controller;

import com.example.product_api.dto.UserRequestDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

@AutoConfigureMockMvc

public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUser() throws Exception {

        UserRequestDTO dto =
                new UserRequestDTO(
                        "Teste",
                        "teste@email.com",
                        "123456",
                        "ADMIN",
                        true
                );

        mockMvc.perform(

                        post("/api/users")

                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )

                                .content(
                                        objectMapper.writeValueAsString(dto)
                                )
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Teste"));
    }
}