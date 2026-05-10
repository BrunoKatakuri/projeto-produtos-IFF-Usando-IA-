package com.example.product_api.controller;

import com.example.product_api.dto.ProductRequestDTO;
import com.example.product_api.dto.ProductResponseDTO;
import com.example.product_api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponseDTO create(
            @RequestBody ProductRequestDTO dto
    ) {

        return service.create(dto);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {

        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id
    ) {

        service.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(
            @PathVariable UUID id,
            @RequestBody ProductRequestDTO dto
    ) {

        return service.update(id, dto);
    }
}





