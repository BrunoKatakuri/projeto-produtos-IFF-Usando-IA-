package com.example.product_api.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponseDTO {

    private UUID id;

    private String name;

    private String email;

    private String role;

    private Boolean active;
}