package com.example.product_api.service;

import com.example.product_api.dto.ProductRequestDTO;
import com.example.product_api.dto.ProductResponseDTO;
import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProductSuccessfully() {

        ProductRequestDTO dto =
                new ProductRequestDTO();

        dto.setName("Notebook");
        dto.setPrice(Double.valueOf(3500));
        dto.setDescription("Notebook gamer");

        Product product =
                new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        when(repository.save(any(Product.class)))
                .thenReturn(product);

        ProductResponseDTO response =
                service.create(dto);

        assertNotNull(response);

        assertEquals(
                "Notebook",
                response.getName()
        );

        verify(repository, times(1))
                .save(any(Product.class));
    }
}